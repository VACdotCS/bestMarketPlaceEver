package com.example.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Orders")
public class Order {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    private LocalDate formation_date;

    @ManyToOne
    @JoinColumn(name = "point_id", referencedColumnName = "point_id")
    private PickupPoint pickupPoint;

    private boolean completed;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderedProduct> products;
}
