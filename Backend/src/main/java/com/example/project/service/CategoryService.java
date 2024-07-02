package com.example.project.service;

import com.example.project.entity.Category;
import com.example.project.repository.CategoryRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepo repository;
    public void saveCategory(Category category) {
        repository.save(category);
    }
}
