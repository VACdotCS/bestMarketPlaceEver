package com.example.project.repository;

import com.example.project.entity.User;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByPhone(String phone);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findByUserId(@Param("id") Integer id);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
