package com.example.project.controller;

import com.example.project.dto.response.*;
import com.example.project.entity.*;
import com.example.project.exception.NoSuchElementFoundException;
import com.example.project.repository.*;
import com.example.project.service.DeliveryStatusService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/merchant")
@AllArgsConstructor
@Slf4j
public class MerchantController {

    private UserRepo userRepository;

    private ProductRepo productRepository;

    private OrderRepo orderRepository;

    private OrderedProductRepo orderedProductRepository;

    private final DeliveryStatusRepo deliveryStatusRepository;

    @GetMapping("/{id}") // +
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
    public ResponseEntity<List<ProductResponse>> getMerchantProducts(@PathVariable Integer id) throws NoSuchElementFoundException, JsonProcessingException {
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
                    it.getCategories().getCategory().getTitle(),
                    merchant.getUser_id()
            )).toList());
        } else {
            throw new NoSuchElementFoundException("Merchant not found");
        }
    }



    @PostMapping("update-order-status")
    public void updateOrderStatus(@RequestBody UpdateOrderStatusRequest updateOrderStatusRequest) throws JsonProcessingException {
        OrderedProduct orderedProduct = orderedProductRepository.findOrderedProductByOrder(
                orderRepository.findAll().stream().filter(order -> Objects.equals(order.getId(), updateOrderStatusRequest.getOrderId())).findFirst().get()
        ); // Cringe, посмотрим, после сесси мб отпуск возьму
        log.info("YEAH");

        Optional<DeliveryStatus> optionalDeliveryStatus = deliveryStatusRepository.findByTitle(
                updateOrderStatusRequest.getApproved() ? "В пути" : "Отказ продавца"
        );
        if (optionalDeliveryStatus.isEmpty()) {
            throw new RuntimeException("не найден статус заказа");
        } else {
            orderedProduct.setDeliveryStatus(optionalDeliveryStatus.get());
            orderedProductRepository.save(orderedProduct);
        }
    }


    @GetMapping("/{id}/requests")
    public List<OrderedProductDTO> getMerchantRequests(@PathVariable Integer id) throws NoSuchElementFoundException {
        Optional<User> merchantOptional = userRepository.findAll().stream().filter(
                user -> user.getRole().equals(Role.MERCHANT)
        ).filter(user -> user.getUser_id().equals(id)).findFirst();
        if(merchantOptional.isPresent()) {
            User merchant = merchantOptional.get();
            return merchant.getOrders().stream().flatMap(order ->
                order.getProducts().stream().map(orderedProduct -> new OrderedProductDTO(
                        new CompactProductDTO(orderedProduct.getProduct()),
                        orderedProduct.getCount(),
                        orderedProduct.getDiscountPrice(),
                        orderedProduct.getDeliveryDays(),
                        orderedProduct.getDeliveryStatus().getTitle(),
                        orderedProduct.getCompletionDate()
                       // merchant.getUser_id()
                ))
            ).toList();
        } else {
            throw new NoSuchElementFoundException("Merchant not found");
        }
    }
}
