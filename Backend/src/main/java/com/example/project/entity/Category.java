package com.example.project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue
    private Integer category_id;

    private String title;

    @OneToMany(mappedBy = "category")
    private List<CategoryProduct> category_products;
}
