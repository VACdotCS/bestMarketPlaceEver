package com.example.project.repository;

import com.example.project.entity.CategoryProduct;
import com.example.project.entity.pk.IDCategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryProductRepo extends JpaRepository<CategoryProduct, IDCategoryProduct> {
}
