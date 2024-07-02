package com.example.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "Category")
public class Category {
    @Id
    private Integer category_id;
    private String title;

    @OneToMany(mappedBy = "category")
    private List<CategoryProduct> category_products;
}
