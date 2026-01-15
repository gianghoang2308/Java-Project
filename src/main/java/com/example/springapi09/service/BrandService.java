package com.example.springapi09.service;

import java.util.List;

import com.example.springapi09.dto.Brand.BrandRequest;
import com.example.springapi09.dto.Brand.BrandResponse;
import com.example.springapi09.dto.Brand.BrandUpdate;

public interface BrandService {
    List<BrandResponse> getAllBrands();
    BrandResponse findById(Integer id);
    String create(BrandRequest brandRequest);
    String update(BrandUpdate brandUpdate, Integer id);
    String delete(Integer id);
}
