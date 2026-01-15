package com.example.springapi09.controller;

import com.example.springapi09.dto.BillingAddress.BillingAddressRequest;
import com.example.springapi09.dto.BillingAddress.BillingAddressResponse;
import com.example.springapi09.dto.BillingAddress.BillingAddressUpdate;
import com.example.springapi09.entity.BillingAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.springapi09.response.ResponseHandle;
import com.example.springapi09.service.BillingAddressService;

import java.util.List;

@RestController
@RequestMapping("/billingaddress")
public class BillingAddressController {

    @Autowired
    private BillingAddressService billingAddressService;

    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        List<BillingAddressResponse> billingAddressResponses = billingAddressService.getAllBillingAddresses();
        return ResponseHandle.responseBuilder("Successfully fetch data from the database!", null, HttpStatus.OK, billingAddressResponses);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Integer id) {
        BillingAddressResponse billingById = billingAddressService.findById(id);
        return ResponseHandle.responseBuilder("Request billing detail", null, HttpStatus.OK, billingById);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody BillingAddressRequest billingAddressRequest) {
        String createBillingAddress = billingAddressService.create(billingAddressRequest);
        return ResponseHandle.responseBuilder("Create new billing address", null, HttpStatus.OK, createBillingAddress);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@RequestBody BillingAddressUpdate billingAddressUpdate, @PathVariable("id") Integer id) {
        String updateBillingAddress = billingAddressService.update(billingAddressUpdate, id);
        return ResponseHandle.responseBuilder(updateBillingAddress, null, HttpStatus.OK, updateBillingAddress);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        String deleteBillingAddress = billingAddressService.delete(id);
        return ResponseHandle.responseBuilder(deleteBillingAddress + "id: " + id, null, HttpStatus.OK, null);
    }


}
