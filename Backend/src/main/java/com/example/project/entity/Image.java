package com.example.project.entity;

import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "Image")
public class Image {
    @Id
    private Integer image_id;
    private String image;
    private String alt;

    @OneToOne(mappedBy = "product_image", optional = true)
    private Product image_product;
}
