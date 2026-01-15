package com.example.springapi09.service;

import java.util.List;

import com.example.springapi09.dto.ProductImage.ProductImageRequest;
import com.example.springapi09.dto.ProductImage.ProductImageResponse;
import com.example.springapi09.dto.ProductImage.ProductImageUpdate;

public interface ProductImageService {

    List<ProductImageResponse> getAllProductImages();

    ProductImageResponse findById(Integer id);

    String create(ProductImageRequest productImageRequest);

    String update(ProductImageUpdate productImageUpdate, Integer id);

    String delete(Integer id);

}
