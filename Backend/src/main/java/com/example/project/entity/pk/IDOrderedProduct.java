package com.example.project.entity.pk;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class IDOrderedProduct implements Serializable {
    private Integer order;
    private Integer product;
}
