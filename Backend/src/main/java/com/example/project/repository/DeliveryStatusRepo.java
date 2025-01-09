package com.example.project.repository;


import com.example.project.entity.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryStatusRepo extends JpaRepository<DeliveryStatus, Integer> {
    @Query("SELECT ds FROM DeliveryStatus ds WHERE ds.status_id = :id")
    Optional<DeliveryStatus> findByStatusId(@Param("id") Integer id);

    @Query("SELECT ds FROM DeliveryStatus ds WHERE ds.title = :title")
    Optional<DeliveryStatus> findByTitle(@Param("title") String title);
}
