package com.example.project.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateOrderStatusRequest {
        @JsonProperty("orderId")
        @NotNull(message = "ID заказа не может быть пустым")
        Integer orderId;

        @JsonProperty("approved")
        @NotNull(message = "Aprroved is not null")
        Boolean approved;
}