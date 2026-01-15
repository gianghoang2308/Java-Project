package com.example.springapi09.controller;

import com.example.springapi09.dto.Contact.ContactRequest;
import com.example.springapi09.dto.Contact.ContactResponse;
import com.example.springapi09.dto.Contact.ContactUpdate;
import com.example.springapi09.response.ResponseHandle;
import com.example.springapi09.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        List<ContactResponse> contacts = contactService.getAllContacts();
        return ResponseHandle.responseBuilder("Successfully fetch contacts form the databse!", null, HttpStatus.OK, contacts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Integer id) {
        ContactResponse contactResponse = contactService.findById(id);
        return ResponseHandle.responseBuilder("Successfully fetch contact's detail from the databse!", null, HttpStatus.OK, contactResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody ContactRequest contactRequest) {
        String message = contactService.create(contactRequest);
        return ResponseHandle.responseBuilder("Successfully created the contact!", null, HttpStatus.OK, message);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody ContactUpdate contactUpdate) {
        String message = contactService.update(contactUpdate, id);
        return ResponseHandle.responseBuilder("Successfully updated the contact!", null, HttpStatus.OK, message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        String message = contactService.delete(id);
        return ResponseHandle.responseBuilder("Successfully deleted the contact!", null, HttpStatus.OK, message);
    }
}
