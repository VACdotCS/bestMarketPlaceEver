package com.example.project.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Product")
public class Product {
    @Id
    private int product_id;
    private String title;

    @OneToOne
    @JoinColumn(name = "image_id", referencedColumnName = "image_id")
    private Image product_image;
    private String description;

    @Column(name = "price", precision = 18, scale = 2)
    private BigDecimal price;

    @Column(name = "discount_price", precision = 18, scale = 2)
    private BigDecimal discountPrice;

    private Integer quantity_of_available;
    private String unit;

    @Column(name = "delivery_days")
    private Integer deliveryDays;

    @OneToOne(mappedBy = "product_category")
    private CategoryProduct categories;

    @OneToMany(mappedBy = "product")
    private List<OrderedProduct> product_orderes;

}
