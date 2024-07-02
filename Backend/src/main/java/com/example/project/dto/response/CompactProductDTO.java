package com.example.project.dto.response;

import com.example.project.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CompactProductDTO {
    private int id;

    private String title;

    private ImageDTO image;

    private String unit;

    public CompactProductDTO(Product product) {
        id = product.getProduct_id();
        title = product.getTitle();
        image = new ImageDTO(
                product.getProduct_image().getImage_id(),
                product.getProduct_image().getImage(),
                product.getProduct_image().getAlt()
        );
        unit = product.getUnit();
    }
}
