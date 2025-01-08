package com.example.project.dto.response;

import java.math.BigDecimal;

public record ProductResponse(
     Integer productId,
     String title,
     String imageName,
     String description,
     BigDecimal price,
     BigDecimal discountPrice,
     Integer quantityOfAvailable,
     String unit,
     Integer deliveryDays,
     Boolean deleted,
     String category
) {}
