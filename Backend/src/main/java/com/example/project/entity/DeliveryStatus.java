package com.example.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Delivery_status")
public class DeliveryStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer status_id;
    private String title;

    @JsonIgnore
    @OneToMany(mappedBy = "deliveryStatus", fetch = FetchType.LAZY)
    private List<OrderedProduct> orderedProducts;
}
