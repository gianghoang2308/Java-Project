package com.example.springapi09.controller;

import com.example.springapi09.dto.ProductImage.ProductImageRequest;
import com.example.springapi09.dto.ProductImage.ProductImageResponse;
import com.example.springapi09.dto.ProductImage.ProductImageUpdate;
import com.example.springapi09.entity.ProductImage;
import com.example.springapi09.exception.NotFoundException;
import com.example.springapi09.response.ResponseHandle;
import com.example.springapi09.service.ProductImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productimage")
public class ProductImageController {

    @Autowired
    private ProductImageService productImageService;

    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        List<ProductImageResponse> productImages = productImageService.getAllProductImages();
        return ResponseHandle.responseBuilder("Successfully fetch product's images from the database!", null, HttpStatus.OK, productImages);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductImageById(@PathVariable Integer id) {
        try {
            ProductImageResponse productImageResponse = productImageService.findById(id);
            return ResponseHandle.responseBuilder(
                    "Successfully fetch details from the database!",
                    null,
                    HttpStatus.OK,
                    productImageResponse);
        } catch (NotFoundException e) {
            return ResponseHandle.responseBuilder(
                    e.getMessage(),
                    null,
                    HttpStatus.NOT_FOUND,
                    null);
        }
    }


    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> createProductImage(@Valid @ModelAttribute ProductImageRequest productImageRequest) {
        String message = productImageService.create(productImageRequest);
        return ResponseHandle.responseBuilder("Successfully create new product images!", null, HttpStatus.OK, message);
    }

    @PutMapping(value = "update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> updateProductImage(@PathVariable Integer id, @Valid @ModelAttribute ProductImageUpdate productImageUpdate) {
        String message = productImageService.update(productImageUpdate, id);
        return ResponseHandle.responseBuilder("Successfully update product images!", null, HttpStatus.OK, message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteProductImage(@PathVariable Integer id) {
        String message = productImageService.delete(id);
        return ResponseHandle.responseBuilder("Successfully delete product images!", null, HttpStatus.OK, message);
    }
}
