package com.example.springapi09.service;

import java.util.List;

import com.example.springapi09.dto.Product.ProductRequest;
import com.example.springapi09.dto.Product.ProductResponse;
import com.example.springapi09.dto.Product.ProductUpdate;

public interface ProductService {
    List<ProductResponse> getAllProducts();
    ProductResponse findById(Integer id);
    String create(ProductRequest productRequest);
    String update(ProductUpdate productUpdate, Integer id);
    String delete(Integer id);
}
