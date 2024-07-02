package com.example.project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageDTO {
    private Integer id;
    private String image;
    private String alt;
}
