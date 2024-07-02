package com.example.project.repository;

import com.example.project.dto.response.UserProductStatsDTO;
import com.example.project.entity.OrderedProduct;
import com.example.project.entity.pk.IDOrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedProductRepo extends JpaRepository<OrderedProduct, IDOrderedProduct> {
    @Query(value = "SELECT op.* FROM ordered_product op " +
            "JOIN orders o ON op.order_id = o.order_id " +
            "WHERE op.status_id = ?1 AND (o.formation_date + make_interval(days \\:= op.delivery_days)) < NOW();",
            nativeQuery = true)
    List<OrderedProduct> findAllProductsDueToArrive(Integer statusId);

    @Query("SELECT new com.example.project.dto.response.UserProductStatsDTO(" +
            "SUM(case when ds.title = 'Выдан' then 1 else 0 end), " +
            "SUM(case when ds.title = 'Отказ' then 1 else 0 end)) " +
            "FROM OrderedProduct op " +
            "JOIN op.order o " +
            "JOIN o.user u " +
            "JOIN op.deliveryStatus ds " +
            "WHERE u.user_id = :userId " +
            "GROUP BY u.user_id")
    UserProductStatsDTO findUserProductStatsByUserId(@Param("userId") Integer userId);
}
