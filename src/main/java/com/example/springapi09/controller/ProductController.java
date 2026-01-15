package com.example.springapi09.controller;

import com.example.springapi09.dto.Product.ProductRequest;
import com.example.springapi09.dto.Product.ProductResponse;
import com.example.springapi09.dto.Product.ProductUpdate;
import com.example.springapi09.entity.Product;
import com.example.springapi09.response.ResponseHandle;
import com.example.springapi09.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        List<ProductResponse> products = productService.getAllProducts();
        return ResponseHandle.responseBuilder("Successfully fetch products from the databse!", null, HttpStatus.OK, products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable("id") Integer id) {
        ProductResponse productResponse = productService.findById(id);
        return ResponseHandle.responseBuilder("Successfully fetch product detail form the databse!", null, HttpStatus.OK, productResponse);
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> createProduct(@Valid @ModelAttribute ProductRequest productRequest) {
        String message = productService.create(productRequest);
        return ResponseHandle.responseBuilder("Create a new product", null, HttpStatus.OK, message);
    }

    @PutMapping(value = "update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> updateProduct(@Valid @ModelAttribute ProductUpdate productUpdate, @PathVariable Integer id) {
        String message = productService.update(productUpdate, id);
        return ResponseHandle.responseBuilder("Update the product", null, HttpStatus.OK, message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Integer id) {
        String message = productService.delete(id);
        return ResponseHandle.responseBuilder("Delete the product", null, HttpStatus.OK, message);
    }
}
