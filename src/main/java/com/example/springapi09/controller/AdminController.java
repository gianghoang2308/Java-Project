package com.example.springapi09.controller;

import java.util.List;

import com.example.springapi09.dto.Brand.BrandUpdate;
import com.example.springapi09.dto.Supplier.SupplierUpdate;
import com.example.springapi09.dto.admin.AdminRegisterUpdate;
import com.example.springapi09.dto.admin.AdminUpdate;
import com.example.springapi09.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springapi09.dto.admin.AdminRequest;
import com.example.springapi09.dto.admin.AdminResponse;
import com.example.springapi09.response.ResponseHandle;
import com.example.springapi09.service.AdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/")
    @Transactional(readOnly = true)
    public ResponseEntity<Object> getAll() {
        List<AdminResponse> adminResponses = adminService.getAllAdmins();
        return ResponseHandle.responseBuilder(
                "Successfully fetch admins from database",
                null,
                HttpStatus.OK,
                adminResponses
        );
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<Object> getById(@PathVariable("id") Integer id) {
        AdminResponse adminResponse = adminService.findById(id);
        return ResponseHandle.responseBuilder("Request an admin's detail", null, HttpStatus.OK, adminResponse);
    }


    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> create(@Valid @ModelAttribute AdminRequest adminRequest) {

        String messageCreate = adminService.create(adminRequest);

        return ResponseHandle.responseBuilder(
                "Create a new admin: " + messageCreate,
                null,
                HttpStatus.CREATED,
                messageCreate
        );
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> update(
            @PathVariable Integer id,
            @Valid @ModelAttribute AdminUpdate adminUpdate
    ) {
        try {
            String message = adminService.update(adminUpdate, id);
            return ResponseHandle.responseBuilder("Update an admin successfully!", null, HttpStatus.OK, message);
        } catch (Exception e) {
            return ResponseHandle.responseBuilder("Error occurred while updating an admin.", null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        String deleteMessage = adminService.delete(id);
        return ResponseHandle.responseBuilder(deleteMessage, null, HttpStatus.OK, null);
    }




}
