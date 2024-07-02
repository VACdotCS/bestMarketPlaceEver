package com.example.project.entity.pk;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class IDCategoryProduct implements Serializable {
    private Integer category;
    private Integer product_category;
}
