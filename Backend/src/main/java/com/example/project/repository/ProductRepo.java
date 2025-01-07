package com.example.project.repository;

import com.example.project.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE Product SET deleted = true WHERE productId=:id")
    void deleteById(Integer id);

}
