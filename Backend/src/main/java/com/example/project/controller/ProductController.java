package com.example.project.controller;

import com.example.project.dto.response.CategoryDTO;
import com.example.project.dto.response.ProductDTO;
import com.example.project.entity.Category;
import com.example.project.entity.CategoryProduct;
import com.example.project.entity.Product;
import com.example.project.entity.User;
import com.example.project.entity.pk.IDCategoryProduct;
import com.example.project.exception.ImageNotExistsException;
import com.example.project.exception.ProductNotFoundException;
import com.example.project.repository.CategoryProductRepo;
import com.example.project.repository.CategoryRepo;
import com.example.project.repository.ProductRepo;
import com.example.project.repository.UserRepo;
import com.example.project.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
@Log4j2
public class ProductController {
    private ProductService productService;
    private CategoryRepo categoryRepo;
    private CategoryProductRepo categoryProductRepo;
    private ProductRepo productRepo;
    private UserRepo userRepo;
    private static final ObjectMapper objectMapper = new ObjectMapper();

//    @GetMapping("/image/{imageId}")
//    public ResponseEntity<byte[]> getImage(@PathVariable Integer imageId) {
//        try {
//            byte[] image = productService.getImage(imageId);
//            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
//        } catch (ImageNotExistsException e) {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        }
//    }

    @PostMapping()
    public ResponseEntity<String> uploadProduct(
            @RequestParam("merchantId") Integer merchantId,
            @RequestParam("image") String file,
            @RequestParam("title") String title,
            @RequestParam("price") BigDecimal price,
            @RequestParam("unit") String unit,
            @RequestParam("discount_price") BigDecimal discount_price,
            @RequestParam("description") String description,
            @RequestParam("quantity_of_available") Integer quantity_of_available,
            @RequestParam("category") String category
    ) {
        try {
            List<Product> products = productRepo.findAll();

            int lastProductId = 0;

            if (!products.isEmpty()) {
                lastProductId = products.get(products.size() - 1).getProductId();
            }

            Optional<User> merchant = this.userRepo.findByUserId(merchantId);

            log.info(objectMapper.writeValueAsString(merchant));
            log.info(merchantId);
            log.info("Запрос прошёл");

            if (merchant.isEmpty()) {
                throw new BadRequestException("");
            }

            if (!merchant.get().getRole().name().equals("MERCHANT")) {
                throw new BadRequestException("");
            }

            Product newProduct = new Product();
            newProduct.setProductId(lastProductId + 1);
            newProduct.setPrice(price);
            newProduct.setTitle(title);
            newProduct.setUnit(unit);
            newProduct.setDiscountPrice(discount_price);
            newProduct.setDeliveryDays(new Random().nextInt(5) + 1);
            newProduct.setImageName(file);
            newProduct.setQuantityOfAvailable(quantity_of_available);
            newProduct.setDescription(description);
            newProduct.setMerchant(merchant.get());

            Optional<Category> categoryCandidate = categoryRepo.findCategoryByTitle(category);
            if (categoryCandidate.isEmpty()) {
                Category newCategory = new Category();
                newCategory.setTitle(category);
                categoryRepo.save(newCategory);
                categoryCandidate = categoryRepo.findCategoryByTitle(category);
            }

            productRepo.save(newProduct);

            CategoryProduct newRelation = new CategoryProduct();
            newRelation.setCategory(categoryCandidate.get());
            newRelation.setProduct_category(newProduct);
            categoryProductRepo.save(newRelation);

            return ResponseEntity.status(HttpStatus.CREATED).body("Successful");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (JsonProcessingException | BadRequestException e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable(value = "id") Integer id,
            @RequestParam(value = "image", required = false) Optional<String> file,
            @RequestParam(value = "title", required = false) Optional<String> title,
            @RequestParam(value = "price", required = false) Optional<BigDecimal> price,
            @RequestParam(value = "unit", required = false) Optional<String> unit,
            @RequestParam(value = "discount_price", required = false) Optional<BigDecimal> discount_price,
            @RequestParam(value = "description", required = false) Optional<String> description,
            @RequestParam(value = "quantity_of_available", required = false) Optional<Integer> quantity_of_available,
            @RequestParam(value = "category", required = false) Optional<String> category
    ) throws ProductNotFoundException {
        Optional<Product> productToUpdate = productRepo.findById(id);

        if (productToUpdate.isEmpty()) {
            throw new ProductNotFoundException("Product not found");
        }

        Product p = productToUpdate.get();

        file.ifPresent(p::setImageName);
        title.ifPresent(p::setTitle);
        price.ifPresent(p::setPrice);
        unit.ifPresent(p::setUnit);
        discount_price.ifPresent(p::setDiscountPrice);
        description.ifPresent(p::setDescription);
        quantity_of_available.ifPresent(p::setQuantityOfAvailable);

        if (category.isPresent()) {
            String presentedCategory = category.get();
            Optional<Category> categoryCandidate = categoryRepo.findCategoryByTitle(presentedCategory);

            if (categoryCandidate.isEmpty()) {
                Category newCategory = new Category();
                newCategory.setTitle(presentedCategory);
                categoryRepo.save(newCategory);
                categoryCandidate = categoryRepo.findCategoryByTitle(presentedCategory);
            }

            productRepo.save(p);

            categoryProductRepo.updateCategoryByProductId(categoryCandidate.get(), p.getProductId());
        } else {
            productRepo.save(p);
        }

        return ResponseEntity.ok(productToUpdate.get());
    }

    @GetMapping("/assortment")
    public ResponseEntity<List<ProductDTO>> getAssortment() {
        List<ProductDTO> products = productService.getAssortment();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAvailableCategories() {
        return ResponseEntity.ok(productService.getAvailableCategories());
    }

    @GetMapping("/special")
    public ResponseEntity<List<ProductDTO>> getSpecialOffers() {
        List<ProductDTO> products = productService.getSpecialOffers();
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
        boolean isDeleted = productService.deleteProductById(id);
        if (isDeleted) {
            return ResponseEntity.ok("Deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
