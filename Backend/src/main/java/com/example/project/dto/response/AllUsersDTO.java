package com.example.project.dto.response;

import com.example.project.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllUsersDTO {
    private Integer id;
    private String username;
    private Role role;
    private BigDecimal amount_spent;
    private Double user_discount;
}
