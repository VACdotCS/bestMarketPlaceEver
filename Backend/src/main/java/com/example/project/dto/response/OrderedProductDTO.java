package com.example.project.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@AllArgsConstructor
public class OrderedProductDTO {

    @JsonProperty("ordered_product")
    private CompactProductDTO product;

    private Integer quantity;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("delivery_days")
    private Integer deliveryDays;

    @JsonProperty("delivery_status")
    private String deliveryStatus;

    @JsonProperty("completion_date")
    private LocalDate completionDate;
}
