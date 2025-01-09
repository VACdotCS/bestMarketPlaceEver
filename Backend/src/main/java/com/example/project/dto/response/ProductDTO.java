package com.example.project.dto.response;

import com.example.project.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductDTO {
    private int id;

    private String title;

    private String image;

    private String description;

    private BigDecimal price;

    @JsonProperty("discount_price")
    private BigDecimal discountPrice;

    @JsonProperty("quantity_available")
    private Integer quantityAvailable;

    private String unit;

    @JsonProperty("delivery_days")

    private Integer deliveryDays;

    private String categories;

    private Integer merchantId;

    public ProductDTO(Product product) {
        id = product.getProductId();
        title = product.getTitle();
        image = product.getImageName();
        description = product.getDescription();
        price = product.getPrice();
        discountPrice = product.getDiscountPrice();
        quantityAvailable = product.getQuantityOfAvailable();
        unit = product.getUnit();
        deliveryDays = product.getDeliveryDays();
        categories = product.getCategories().getCategory().getTitle();
        merchantId = product.getMerchant().getUser_id();
    }
}
