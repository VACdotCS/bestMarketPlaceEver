package com.example.project.controller;

import com.example.project.dto.response.*;
import com.example.project.entity.*;
import com.example.project.exception.NoSuchElementFoundException;
import com.example.project.repository.OrderRepo;
import com.example.project.repository.OrderedProductRepo;
import com.example.project.repository.ProductRepo;
import com.example.project.repository.UserRepo;
import com.example.project.service.DeliveryStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private UserRepo userRepository;

    private ProductRepo productRepository;

    private OrderRepo orderRepository;

    private OrderedProductRepo orderedProductRepository;

    @GetMapping("/{id}")
    public ResponseEntity<SellerInfo> getMerchantInfo(@PathVariable Integer id) throws NoSuchElementFoundException {
        Optional<User> merchantOptional = userRepository.findAll().stream().filter(
            user -> user.getRole().equals(Role.MERCHANT)
        ).filter(user -> user.getUser_id().equals(id)).findFirst();
        if(merchantOptional.isPresent()) {
            User merchant = merchantOptional.get();
            Double income = merchant.getOrders().stream()
                .filter(Order::isCompleted)
                .flatMap(order -> order.getProducts().stream())
                .reduce(BigDecimal.ZERO,
                    (bigDecimal, orderedProduct) -> bigDecimal.add(
                        orderedProduct.getProduct().getPrice().multiply(
                            BigDecimal.valueOf(orderedProduct.getCount())
                        )
                    ), BigDecimal::add
                ).doubleValue();
            return ResponseEntity.ok(new SellerInfo(income));
        } else {
            throw new NoSuchElementFoundException("Merchant not found");
        }
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<ProductResponse>> getMerchantProducts(@PathVariable Integer id) throws NoSuchElementFoundException {
        Optional<User> merchantOptional = userRepository.findAll().stream().filter(
                user -> user.getRole().equals(Role.MERCHANT)
        ).filter(user -> user.getUser_id().equals(id)).findFirst();
        if(merchantOptional.isPresent()) {
            User merchant = merchantOptional.get();
            return ResponseEntity.ok(productRepository.getProductsByMerchant(merchant).stream().map(it -> new ProductResponse(
                    it.getProductId(),
                    it.getTitle(),
                    it.getImageName(),
                    it.getDescription(),
                    it.getPrice(),
                    it.getDiscountPrice(),
                    it.getQuantityOfAvailable(),
                    it.getUnit(),
                    it.getDeliveryDays(),
                    it.getDeleted(),
                    it.getCategories().getCategory().getTitle()
            )).toList());
        } else {
            throw new NoSuchElementFoundException("Merchant not found");
        }
    }

    @PostMapping("update-order-status")
    public void updateOrderStatus(UpdateOrderStatusRequest updateOrderStatusRequest) {
        OrderedProduct orderedProduct = orderedProductRepository.findOrderedProductByOrder(
                orderRepository.getReferenceById(updateOrderStatusRequest.orderId())
        );
        orderedProduct.setDeliveryStatus(
                DeliveryStatus.builder().title(
                        updateOrderStatusRequest.approved() ? "в пути" : "отказ продавца"
                ).build()
        );
    }

    @PostMapping("/{id}/requests")
    public List<OrderedProductDTO> getMerchantRequests(@PathVariable Integer id) throws NoSuchElementFoundException {
        Optional<User> merchantOptional = userRepository.findAll().stream().filter(
                user -> user.getRole().equals(Role.MERCHANT)
        ).filter(user -> user.getUser_id().equals(id)).findFirst();
        if(merchantOptional.isPresent()) {
            User merchant = merchantOptional.get();
            return merchant.getOrders().stream().flatMap(order ->
                order.getProducts().stream().map(orderedProduct -> new OrderedProductDTO(
                        new CompactProductDTO(orderedProduct.getProduct()),
                        orderedProduct.getProduct().getQuantityOfAvailable(),
                        orderedProduct.getDiscountPrice(),
                        orderedProduct.getDeliveryDays(),
                        orderedProduct.getDeliveryStatus().getTitle(),
                        orderedProduct.getCompletionDate()
                ))
            ).toList();
        } else {
            throw new NoSuchElementFoundException("Merchant not found");
        }
    }
}
