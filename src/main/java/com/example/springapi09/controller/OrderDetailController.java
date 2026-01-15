package com.example.springapi09.controller;

import com.example.springapi09.dto.OrderDetail.OrderDetailRequest;
import com.example.springapi09.dto.OrderDetail.OrderDetailResponse;
import com.example.springapi09.entity.OrderDetail;
import com.example.springapi09.response.ResponseHandle;
import com.example.springapi09.service.OrderDetailService;
import com.fasterxml.jackson.core.ObjectCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orderdetail")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        List<OrderDetailResponse> orderDetailResponses = orderDetailService.getAllOrderDetails();
        return ResponseHandle.responseBuilder("Successfully fetch order from the database!", null, HttpStatus.OK, orderDetailResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOne(@PathVariable Integer id) {
        OrderDetailResponse orderDetailResponse = orderDetailService.findById(id);
        return ResponseHandle.responseBuilder("Successfully fetch orderdeatail's detail from the database!", null, HttpStatus.OK, orderDetailResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody OrderDetailRequest orderDetailRequest) {
        String message = orderDetailService.create(orderDetailRequest);
        return ResponseHandle.responseBuilder("Successfully create a new order detail", null, HttpStatus.OK, message);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody OrderDetailRequest orderDetailRequest) {
        String message = orderDetailService.update(orderDetailRequest, id);
        return ResponseHandle.responseBuilder("Successfully update the order detail", null, HttpStatus.OK, message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        String message = orderDetailService.delete(id);
        return ResponseHandle.responseBuilder("Successfully delete the order detail", null, HttpStatus.OK, message);
    }
}
