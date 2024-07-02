package com.example.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import java.util.List;

@Data
@Entity
@Table(name = "Pickup_point")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PickupPoint {
    @Id
    @Column(name = "point_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String address;
    @OneToOne(optional = true)
    @JoinColumn(name = "manager_id", referencedColumnName = "user_id")
    private User manager;

    private Double income;

    @OneToMany(mappedBy = "pickupPoint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

    @PreRemove
    private void preRemove() {
        for (Order order : orders) {
            order.setPickupPoint(null);
        }
    }
}
