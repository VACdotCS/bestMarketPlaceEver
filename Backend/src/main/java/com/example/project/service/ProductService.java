package com.example.project.service;

import com.example.project.dto.response.CategoryDTO;
import com.example.project.dto.response.ProductDTO;
import com.example.project.entity.CategoryProduct;
import com.example.project.entity.Product;
import com.example.project.exception.ImageNotExistsException;
import com.example.project.repository.CategoryProductRepo;
import com.example.project.repository.ProductRepo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepo productRepo;
    //private CategoryProductRepo categoryProductRepo;

//    @Transactional
//    public Image uploadImage(MultipartFile file) throws IOException {
//        List<Image> images = imageRepo.findAll();
//        int lastImageId = images.get(images.size() - 1).getImageId();
//        byte[] imageByteArr = file.getBytes();
//        Image newImage = new Image();
//        newImage.setImage(imageByteArr);
//        newImage.setAlt("");
//        newImage.setImageId(lastImageId + 1);
//        imageRepo.save(newImage);
//        return newImage;
//    }

//    @Transactional
//    public byte[] getImage(Integer imageId) throws ImageNotExistsException {
//        Optional<Image> image = imageRepo.findById(imageId);
//        if (image.isEmpty()) {
//            throw new ImageNotExistsException("Image doesn't exists");
//        }
//        return image.get().getImage();
//    }

    public List<CategoryDTO> getAvailableCategories() {
        Map<String, Integer> categoriesAndCount = new HashMap<>();
        List<CategoryDTO> response = new ArrayList<>();
        List<Product> products = productRepo.findAll().stream().filter(el -> !el.getDeleted()).toList();
        products.forEach(el -> {
            String category = el.getCategories().getCategory().getTitle();
            if (categoriesAndCount.get(category) == null) {
                categoriesAndCount.put(category, 1);
            } else {
                categoriesAndCount.replace(category, categoriesAndCount.get(category) + 1);
            }
        });
        categoriesAndCount.forEach((key, value) -> {
            CategoryDTO res = new CategoryDTO();
            res.setName(key);
            res.setCount(value);
            response.add(res);
        });
        return response;
    }

    public List<ProductDTO> getAssortment() {
        return productRepo.findAll().stream().filter(el -> !el.getDeleted()).map(ProductDTO::new).toList();
    }

    public List<ProductDTO> getSpecialOffers() {
        return productRepo.findAll().stream().filter(el -> !el.getDeleted()).map(ProductDTO::new).filter(el -> {
            int resultOfComparation = el.getPrice().compareTo(el.getDiscountPrice());
            return resultOfComparation > 0;
        }).toList();
    }

    public boolean deleteProductById(Integer id) {
        if (productRepo.existsById(id) && !productRepo.findById(id).get().getDeleted()) {
            productRepo.deleteById(id);
            Optional<Product> p = productRepo.findById(id);
            //categoryProductRepo.deleteByProductId(id);

            p.ifPresent(product -> deleteImage(product.getImageName()));

            return true;
        } else {
            return false;
        }
    }

    public void deleteImage(String imageName) {
        RestTemplate template = new RestTemplate();
        String uri = "http://localhost:3000/images/" + imageName;

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-static-key", "SUPER_SECRET_KEY");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        template.exchange(uri, HttpMethod.DELETE, entity, String.class);
    }
}
