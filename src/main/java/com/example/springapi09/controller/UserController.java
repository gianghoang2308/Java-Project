package com.example.springapi09.controller;

import com.example.springapi09.dto.User.UserRegisterUpdate;
import com.example.springapi09.dto.User.UserRequest;
import com.example.springapi09.dto.User.UserResponse;
import com.example.springapi09.dto.User.UserUpdate;

import com.example.springapi09.dto.admin.AdminRegisterUpdate;
import com.example.springapi09.entity.Admin;
import com.example.springapi09.entity.User;
import com.example.springapi09.response.ResponseHandle;
import com.example.springapi09.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    @Transactional(readOnly = true)
    public ResponseEntity<Object> getAll() {
        List<UserResponse> userResponses = userService.getAllUsers();
        return ResponseHandle.responseBuilder(
                "Successfully fetch users from database",
                null,
                HttpStatus.OK,
                userResponses
        );
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<Object> getById(@PathVariable("id") Integer id) {
        UserResponse userResponse = userService.findById(id);
        return ResponseHandle.responseBuilder("Request an user's detail", null, HttpStatus.OK, userResponse);
    }


    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> create(@Valid @ModelAttribute UserRequest userRequest) {

        String messageCreate = userService.create(userRequest);

        return ResponseHandle.responseBuilder(
                "Create a new user: " + messageCreate,
                null,
                HttpStatus.CREATED,
                messageCreate
        );
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> update(
            @PathVariable Integer id,
            @Valid @ModelAttribute UserUpdate userUpdate
    ) {
        try {
            String message = userService.update(userUpdate, id);
            return ResponseHandle.responseBuilder("Update an user successfully!", null, HttpStatus.OK, message);
        } catch (Exception e) {
            return ResponseHandle.responseBuilder("Error occurred while updating an user.", null, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        String deleteMessage = userService.delete(id);
        return ResponseHandle.responseBuilder(deleteMessage, null, HttpStatus.OK, null);
    }


}
