package com.example.springapi09.service.Impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.springapi09.dto.Category.CategoryUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapi09.dto.Category.CategoryRequest;
import com.example.springapi09.dto.Category.CategoryResponse;
import com.example.springapi09.entity.Category;
import com.example.springapi09.exception.NotFoundException;
import com.example.springapi09.mapper.CategoryMapper;
import com.example.springapi09.repository.CategoryRepository;
import com.example.springapi09.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new NotFoundException("Data is null");
        }
        return categoryMapper.toResponseList(categories);
    }

    @Override
    public CategoryResponse findById(Integer id) {
        Optional<Category> category = categoryRepository.findById(Objects.requireNonNull(id));
        if (category.isEmpty()) {
            throw new NotFoundException("Data cannot be null!");
        }
        return categoryMapper.toCategoryResponse(category.get());
    }

    @Override
    public String create(CategoryRequest categoryRequest) {
        if (categoryRequest == null) {
            throw new NotFoundException("Data cannot be null!");
        }
        Category create = categoryMapper.toCategory(categoryRequest);
        categoryRepository.save(Objects.requireNonNull(create));
        return "Successfully create new category!";
    }

    @Override
    public String update(CategoryUpdate categoryUpdate, Integer id) {
        if (categoryUpdate == null) {
            throw new NotFoundException("Data cannot be null!");
        }

        if (id == null) {
            throw new NotFoundException("Id cannot be null!");
        }

        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cannot find category with id: " + id));

        if (categoryUpdate.getName() != null && !categoryUpdate.getName().isEmpty()) {
            existing.setName(categoryUpdate.getName());
        }

        categoryRepository.save(existing);
        return "Successfully updated category with id: " + id;
    }

    @Override
    public String delete(Integer id) {
        Optional<Category> category = categoryRepository.findById(Objects.requireNonNull(id));
        if (category.isEmpty()) {
            throw new NotFoundException("Data cannot be null!");
        }
        categoryRepository.deleteById(Objects.requireNonNull(id));
        return "Successfully delete category with id: " + id;
    }
}
