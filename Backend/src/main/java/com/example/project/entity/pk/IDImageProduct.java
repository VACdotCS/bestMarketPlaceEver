package com.example.project.entity.pk;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class IDImageProduct implements Serializable {
    private Integer image_id;
    private Integer product_id;
}
