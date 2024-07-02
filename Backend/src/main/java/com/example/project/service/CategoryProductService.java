package com.example.project.service;

import com.example.project.entity.CategoryProduct;
import com.example.project.repository.CategoryProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryProductService {
    private CategoryProductRepo repository;
    public void saveCategory_product(CategoryProduct category_product) {
        repository.save(category_product);
    }
}
