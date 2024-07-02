package com.example.project.repository;

import com.example.project.entity.PickupPoint;
import com.example.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PickupPointRepo extends JpaRepository<PickupPoint, Integer> {
    @Query("SELECT pp FROM PickupPoint pp JOIN FETCH pp.manager")
    List<PickupPoint> findAllWithManager();

    PickupPoint findByManager(User manager);
}
