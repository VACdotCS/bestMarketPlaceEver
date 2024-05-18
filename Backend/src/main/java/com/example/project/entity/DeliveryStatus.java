package com.example.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Delivery_status")
public class DeliveryStatus {
    @Id
    private Integer status_id;
    private String title;

    @OneToMany(mappedBy = "deliveryStatus")
    private List<OrderedProduct> orderedProducts;
}
