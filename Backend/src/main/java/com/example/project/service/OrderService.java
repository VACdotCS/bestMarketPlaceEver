package com.example.project.service;

import com.example.project.common.Constants;
import com.example.project.dto.mapper.OrderDTOMapper;
import com.example.project.dto.request.ConfirmOrderRequest;
import com.example.project.dto.request.OrderRequest;
import com.example.project.dto.request.OrderedProductRequest;
import com.example.project.dto.response.OrderDTO;
import com.example.project.entity.*;
import com.example.project.exception.*;
import com.example.project.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepo repository;
    private ProductRepo productRepository;
    private PickupPointRepo pickupPointRepo;
    private OrderedProductRepo orderedProductRepo;
    private DeliveryStatusRepo deliveryStatusRepo;
    private UserRepo userRepo;

    private OrderDTOMapper dtoMapper;

    // --> UserService
    private void userRecalculateDiscount(User user) {
        BigDecimal amountSpent = user.getAmount_spent();

        if (amountSpent.compareTo(new BigDecimal("100000")) > 0) {
            user.setUserDiscount(10.0);
        } else if (amountSpent.compareTo(new BigDecimal("75000")) > 0) {
            user.setUserDiscount(7.5);
        } else if (amountSpent.compareTo(new BigDecimal("50000")) > 0) {
            user.setUserDiscount(5.0);
        } else if (amountSpent.compareTo(new BigDecimal("25000")) > 0) {
            user.setUserDiscount(2.5);
        }
    }

    // --> UserService
    private void userAddAmountSpent(User user, BigDecimal orderCost) {
        BigDecimal amountSpent;
        BigDecimal userAmountSpent = user.getAmount_spent();
        if (userAmountSpent != null) {
            amountSpent = userAmountSpent;
        } else {
            amountSpent = new BigDecimal(0);
        }
        BigDecimal newAmountSpent = amountSpent.add(orderCost);
        user.setAmount_spent(newAmountSpent);

        BigDecimal boundary = new BigDecimal("25000");
        BigDecimal oldBoundary = amountSpent.divideToIntegralValue(boundary);
        BigDecimal newBoundary = newAmountSpent.divideToIntegralValue(boundary);
        if (amountSpent.compareTo(new BigDecimal("100000")) < 0 && newBoundary.compareTo(oldBoundary) > 0) {
            userRecalculateDiscount(user);
        }
    }

    private BigDecimal getFinalDiscountPrice(Product product, User user) {
        Double userDiscount = user.getUserDiscount();
        BigDecimal productDiscountPrice = product.getDiscountPrice();

        if (userDiscount != null && userDiscount != 0) {
            BigDecimal discountMultiplier = BigDecimal.valueOf(userDiscount).divide(BigDecimal.valueOf(100));
            return productDiscountPrice.subtract(productDiscountPrice.multiply(discountMultiplier));
        }

        return productDiscountPrice;
    }

    public OrderDTO findAllProductsByOrderId(Integer orderId) throws NoSuchElementFoundException {
        Order order = repository.findById(orderId)
                .orElseThrow(NoSuchElementFoundException::new);

        return dtoMapper.apply(order);
    }

    public List<OrderDTO> findAllByUser() throws NoSuchElementFoundException {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<Order> orders = repository.findAllByUser(user);

        return orders
                .stream()
                .map(dtoMapper)
                .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = {Exception.class})
    public void create(OrderRequest orderRequest) throws NoSuchElementFoundException, ProductsCountMismatchException {
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        PickupPoint pickupPoint = pickupPointRepo
                .findById(orderRequest.getPickupPointId())
                .orElseThrow(() -> new NoSuchElementFoundException(Constants.NOT_FOUND_PICKUPPOINT));
        DeliveryStatus deliveryStatus = deliveryStatusRepo.findById(1).orElseThrow(); // id1 = В пути

        List<OrderedProductRequest> orderedProductsReq = orderRequest.getOrderedProducts();
        List<OrderedProduct> orderedProductsToCreate = new ArrayList<>();
        List<Product> productsToUpdate = new ArrayList<>();

        Order order = Order.builder()
                .user(user)
                .formation_date(LocalDate.now())
                .pickupPoint(pickupPoint)
                .completed(false)
                .build();
        repository.save(order);

        var productsIds = orderedProductsReq
                .stream()
                .map(OrderedProductRequest::getProductId)
                .toList();
        List<Product> products = productRepository.findAllById(productsIds);

        for (OrderedProductRequest orderedProductReq : orderedProductsReq) {
            var id = orderedProductReq.getProductId();
            Product product;
            try {
                product = products
                        .stream()
                        .filter(p -> p.getProduct_id() == id)
                        .toList()
                        .get(0);
            }
            catch (Exception e) {
                throw new NoSuchElementFoundException(Constants.NOT_FOUND_PRODUCT + id.toString());
            }

            var productQuantityAvailable = product.getQuantity_of_available();
            if (productQuantityAvailable.compareTo(orderedProductReq.getCountProduct()) < 0) {
                throw new ProductsCountMismatchException(product.getTitle() + " - такого количества товара нет в наличии");
            }

            BigDecimal discountPrice = getFinalDiscountPrice(product, user);

            orderedProductsToCreate.add(OrderedProduct.builder()
                    .order(order)
                    .product(product)
                    .count(orderedProductReq.getCountProduct())
                    .discountPrice(discountPrice)
                    .deliveryDays(product.getDeliveryDays())
                    .deliveryStatus(deliveryStatus)
                    .build()
            );
            product.setQuantity_of_available(productQuantityAvailable - orderedProductReq.getCountProduct());
            productsToUpdate.add(product);
        }
        userAddAmountSpent(user, orderRequest.getOrderPrice());

        userRepo.save(user);
        productRepository.saveAll(productsToUpdate);
        orderedProductRepo.saveAll(orderedProductsToCreate);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void confirmOrder(Integer id, ConfirmOrderRequest request) throws NoSuchElementFoundException, ProductsCountMismatchException, OrderAlreadyCompletedException {
        Order order = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementFoundException(Constants.NOT_FOUND_ORDER));
        if (order.isCompleted()) {
            throw new OrderAlreadyCompletedException(Constants.ORDER_ALREADY_COMPLETED);
        }
        List<OrderedProduct> products = order.getProducts();

        DeliveryStatus receivedStatus = deliveryStatusRepo.findById(3)
                .orElseThrow(() -> new RuntimeException(Constants.UNKNOWN_ERROR));
        DeliveryStatus returnedStatus = deliveryStatusRepo.findById(4)
                .orElseThrow(() -> new RuntimeException(Constants.UNKNOWN_ERROR));

        var received = request.getReceived();
        var returned = request.getReturned();
        var intersection = new ArrayList<>(returned);
        intersection.retainAll(received);

        if (received.size() + returned.size() != products.size() || !intersection.isEmpty()) {
            throw new ProductsCountMismatchException(Constants.PRODUCT_COUNT_MISMATCH);
        }

        for (OrderedProduct product : products) {
            var productId = product.getProduct().getProduct_id();
            if (!received.contains(productId) && !returned.contains(productId)) {
                throw new ProductsCountMismatchException(Constants.PRODUCT_COUNT_MISMATCH);
            }

            if (received.contains(productId)) {
                product.setDeliveryStatus(receivedStatus);
            } else {
                product.setDeliveryStatus(returnedStatus);
            }
            product.setCompletionDate(LocalDate.now());
        }
        order.setCompleted(true);
        repository.save(order);

        orderedProductRepo.saveAll(products);
    }
}
