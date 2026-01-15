package com.example.springapi09.controller;

import com.example.springapi09.dto.Category.CategoryRequest;
import com.example.springapi09.dto.Category.CategoryResponse;
import com.example.springapi09.dto.Category.CategoryUpdate;
import com.example.springapi09.entity.Category;
import com.example.springapi09.response.ResponseHandle;
import com.example.springapi09.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<Object> getAllCategories() {
        List<CategoryResponse> categoryResponses = categoryService.getAllCategories();
        return ResponseHandle.responseBuilder("Request categories", null, HttpStatus.OK, categoryResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Integer id) {
        CategoryResponse categoryResponse = categoryService.findById(id);
        return ResponseHandle.responseBuilder("Request a category detail", null, HttpStatus.OK, categoryResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createCategory(@RequestBody CategoryRequest categoryRequest) {
        String message = categoryService.create(categoryRequest);
        return ResponseHandle.responseBuilder("Create a new category", null, HttpStatus.OK, message);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") Integer id, @RequestBody CategoryUpdate categoryUpdate) {
        String message = categoryService.update(categoryUpdate, id);
        return ResponseHandle.responseBuilder("Update a category with id: "+id, null, HttpStatus.OK, message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable("id") Integer id) {
        String message = categoryService.delete(id);
        return ResponseHandle.responseBuilder("Delete a category with id: "+id, null, HttpStatus.OK, message);
    }
}
