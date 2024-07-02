package com.example.project.controller;

import com.example.project.dto.request.ConfirmOrderRequest;
import com.example.project.dto.request.OrderRequest;
import com.example.project.exception.NoSuchElementFoundException;
import com.example.project.exception.OrderAlreadyCompletedException;
import com.example.project.exception.ProductsCountMismatchException;
import com.example.project.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderedProductsByOrderId(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(orderService.findAllProductsByOrderId(id), HttpStatus.OK);
        } catch (NoSuchElementFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<?> getOrdersByUser() {
        try {
            return new ResponseEntity<>(orderService.findAllByUser(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderRequest request) {
        try {
            orderService.create(request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementFoundException | ProductsCountMismatchException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> confirmOrder(
            @PathVariable Integer id, @RequestBody ConfirmOrderRequest request
    ) {
        try {
            orderService.confirmOrder(id, request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementFoundException | ProductsCountMismatchException | OrderAlreadyCompletedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
