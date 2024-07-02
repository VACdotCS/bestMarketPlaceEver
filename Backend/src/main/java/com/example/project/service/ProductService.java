package com.example.project.service;

import com.example.project.entity.Product;
import com.example.project.repository.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepo repository;

    public void saveProduct(Product product){
        repository.save(product);
    }
}
