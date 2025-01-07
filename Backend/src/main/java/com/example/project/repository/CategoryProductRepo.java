package com.example.project.repository;

import com.example.project.entity.Category;
import com.example.project.entity.CategoryProduct;
import com.example.project.entity.pk.IDCategoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CategoryProductRepo extends JpaRepository<CategoryProduct, IDCategoryProduct> {
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE CategoryProduct cp SET cp.category = :newCategory WHERE cp.product_category.id = :productId")
    void updateCategoryByProductId(Category newCategory, Integer productId);

}
