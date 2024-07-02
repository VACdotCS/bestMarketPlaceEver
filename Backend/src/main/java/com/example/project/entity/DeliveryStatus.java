package com.example.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "Delivery_status")
public class DeliveryStatus {
    @Id
    private Integer status_id;
    private String title;

    @JsonIgnore
    @OneToMany(mappedBy = "deliveryStatus", fetch = FetchType.LAZY)
    private List<OrderedProduct> orderedProducts;
}
