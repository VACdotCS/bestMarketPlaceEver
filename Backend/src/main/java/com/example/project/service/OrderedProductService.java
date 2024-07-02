package com.example.project.service;

import com.example.project.entity.DeliveryStatus;
import com.example.project.entity.OrderedProduct;
import com.example.project.exception.DeliveredProductsNotExistException;
import com.example.project.repository.DeliveryStatusRepo;
import com.example.project.repository.OrderedProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class OrderedProductService {
    private OrderedProductRepo repository;
    private DeliveryStatusRepo deliveryStatusRepo;

    public void updateDeliveryStatuses() throws NoSuchElementException, DeliveredProductsNotExistException {
        List<OrderedProduct> orderedProducts = repository.findAllProductsDueToArrive(1);
        if (orderedProducts.isEmpty()) {
            throw new DeliveredProductsNotExistException();
        }

        DeliveryStatus readyStatus = deliveryStatusRepo.findById(2)
                .orElseThrow(NoSuchElementException::new);

        orderedProducts.forEach(orderedProduct ->
                orderedProduct.setDeliveryStatus(readyStatus));

        repository.saveAll(orderedProducts);
    }
}
