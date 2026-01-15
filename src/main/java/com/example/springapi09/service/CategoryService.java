package com.example.springapi09.service;

import java.util.List;

import com.example.springapi09.dto.Category.CategoryRequest;
import com.example.springapi09.dto.Category.CategoryResponse;
import com.example.springapi09.dto.Category.CategoryUpdate;

public interface CategoryService {

    List<CategoryResponse> getAllCategories();

    CategoryResponse findById(Integer id);

    String create(CategoryRequest categoryRequest);

    String update(CategoryUpdate categoryUpdate, Integer id);

    String delete(Integer id);
}
