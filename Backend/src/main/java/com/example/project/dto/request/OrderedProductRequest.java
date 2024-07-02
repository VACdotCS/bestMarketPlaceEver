package com.example.project.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderedProductRequest {
    @JsonProperty("product_id")
    @NotNull(message = "ID продукта не может быть пустым")
    private Integer productId;

    @JsonProperty("count")
    @NotNull(message = "Количество продукта не может быть пустым")
    @PositiveOrZero(message="Кол-во продукта должно быть положительным числом")
    private Integer countProduct;
}
