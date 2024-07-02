package com.example.project.service;

import com.example.project.entity.DeliveryStatus;
import com.example.project.repository.DeliveryStatusRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeliveryStatusService {
    private DeliveryStatusRepo repository;

    public void saveDelivery_status(DeliveryStatus delivery_status){
        repository.save(delivery_status);
    }
}
