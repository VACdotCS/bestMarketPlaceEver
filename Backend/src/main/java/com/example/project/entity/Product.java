package com.example.project.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "title")
    private String title;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "description")
    private String description;

    @Column(name = "price", precision = 18, scale = 2)
    private BigDecimal price;

    @Column(name = "discount_price", precision = 18, scale = 2)
    private BigDecimal discountPrice;

    @Column(name = "quantity_of_available")
    private Integer quantityOfAvailable;

    @Column(name = "unit")
    private String unit;

    @Column(name = "delivery_days")
    private Integer deliveryDays;

    @Column(columnDefinition = "boolean default false")
    private Boolean deleted = Boolean.FALSE;

    @OneToOne(mappedBy = "product_category")
    private CategoryProduct categories;

    @OneToMany
    private List<OrderedProduct> productOrders;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User merchant;
}