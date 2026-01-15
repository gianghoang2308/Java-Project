package com.example.springapi09.controller;

import com.example.springapi09.dto.Brand.BrandRequest;
import com.example.springapi09.dto.Brand.BrandResponse;
import com.example.springapi09.dto.Brand.BrandUpdate;
import com.example.springapi09.dto.admin.AdminRequest;
import com.example.springapi09.dto.admin.AdminUpdate;
import com.example.springapi09.response.ResponseHandle;
import com.example.springapi09.service.BrandService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        List<BrandResponse> brandResponses = brandService.getAllBrands();
        return ResponseHandle.responseBuilder("Successfully fetch brands from the database!", null, HttpStatus.OK, brandResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Integer id) {
        BrandResponse brandResponse = brandService.findById(id);
        return ResponseHandle.responseBuilder("Request brand detail", null, HttpStatus.OK, brandResponse);
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> create(@Valid @ModelAttribute BrandRequest brandRequest) {
        String message = brandService.create(brandRequest);
        return ResponseHandle.responseBuilder("Create a new brand successfully!", null, HttpStatus.OK, message);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> update(
            @PathVariable Integer id,
            @Valid @ModelAttribute BrandUpdate brandUpdate
    ) {
        try {
            String message = brandService.update(brandUpdate, id);
            return ResponseHandle.responseBuilder("Update a brand successfully!", null, HttpStatus.OK, message);
        } catch (Exception e) {
            return ResponseHandle.responseBuilder("Error occurred while updating brand.", null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        String message = brandService.delete(id);
        return ResponseHandle.responseBuilder("Delete a brand successfully!", null, HttpStatus.OK, message);
    }
}
