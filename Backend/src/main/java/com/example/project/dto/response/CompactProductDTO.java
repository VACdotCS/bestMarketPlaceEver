package com.example.project.dto.response;

import com.example.project.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompactProductDTO {
    private int id;

    private String title;

    private String image;

    private String unit;

    public CompactProductDTO(Product product) {
        id = product.getProductId();
        title = product.getTitle();
        image = product.getImageName();
        unit = product.getUnit();
    }
}
