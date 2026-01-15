package com.example.springapi09.controller;


import com.example.springapi09.dto.Contact.GlobalBindingConfig;
import com.example.springapi09.dto.Supplier.SupplierRequest;
import com.example.springapi09.dto.Supplier.SupplierResponse;
import com.example.springapi09.dto.Supplier.SupplierUpdate;
import com.example.springapi09.response.ResponseHandle;
import com.example.springapi09.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;


    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        List<SupplierResponse> supplierResponses = supplierService.getAllSuppliers();
        return ResponseHandle.responseBuilder("Successfully fetch suppliers from the database!", null, HttpStatus.OK, supplierResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Integer id) {
        SupplierResponse supplierResponse = supplierService.findById(id);
        return ResponseHandle.responseBuilder("Successfully fetch supplier detail from the database!", null, HttpStatus.OK, supplierResponse);
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> create(@Valid @ModelAttribute SupplierRequest supplierRequest) {
        String message = supplierService.create(supplierRequest);
        return ResponseHandle.responseBuilder("Create a new supplier successfully!", null, HttpStatus.OK, message);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> update(
            @PathVariable Integer id,
            @Valid @ModelAttribute SupplierUpdate supplierUpdate
    ) {
        try {
            String message = supplierService.update(supplierUpdate, id);
            return ResponseHandle.responseBuilder("Update a supplier successfully!", null, HttpStatus.OK, message);
        } catch (Exception e) {
            return ResponseHandle.responseBuilder("Error occurred while updating supplier.", null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        String message = supplierService.delete(id);
        return ResponseHandle.responseBuilder("Successfully delete the supplier from the database!", null, HttpStatus.OK, message);
    }
}
