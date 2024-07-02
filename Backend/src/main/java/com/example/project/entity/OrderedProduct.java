package com.example.project.entity;

import com.example.project.entity.pk.IDOrderedProduct;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Ordered_product")
@IdClass(IDOrderedProduct.class)
public class OrderedProduct {
    @Id
    @MapsId("order_id")
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    private Order order;

    @Id
    @MapsId("product_id")
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    @Column(name = "count_product")
    private Integer count;

    @Column(name = "discount_price")
    private BigDecimal discountPrice;

    @Column(name = "delivery_days")
    private Integer deliveryDays;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    private DeliveryStatus deliveryStatus;

    @Column(name = "completion_date")
    private LocalDate completionDate;
}
