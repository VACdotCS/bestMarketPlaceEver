package com.example.project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
    private String username;
    private String email;
    private String phone;
    private String card_number;
    private Integer CVC;
    private LocalDate validity;
    private Double user_discount;
    private BigDecimal amount_spent;
    private Integer order_count;
    private Double redemption_percent;
}
