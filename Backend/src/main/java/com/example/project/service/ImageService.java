package com.example.project.service;

import com.example.project.entity.Image;
import com.example.project.repository.ImageRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ImageService {
    private ImageRepo repository;

    public void saveImage(Image image) {
        repository.save(image);
    }
}