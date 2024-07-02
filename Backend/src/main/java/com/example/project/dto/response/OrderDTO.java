package com.example.project.dto.response;

import com.example.project.entity.OrderedProduct;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderDTO {
    private Integer order_id;
    private List<OrderedProductDTO> products;
    private LocalDate datetime;
    private boolean completed;
}
