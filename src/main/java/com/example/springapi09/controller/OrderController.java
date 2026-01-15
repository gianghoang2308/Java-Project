package com.example.springapi09.controller;

import com.example.springapi09.dto.Order.OrderRequest;
import com.example.springapi09.dto.Order.OrderResponse;
import com.example.springapi09.response.ResponseHandle;
import com.example.springapi09.service.ContactService;
import com.example.springapi09.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        List<OrderResponse> orderResponses = orderService.getAllOrders();
        return ResponseHandle.responseBuilder("Successfully fetch order from the database!", null, HttpStatus.OK, orderResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Integer id) {
        OrderResponse orderResponse = orderService.findById(id);
        return ResponseHandle.responseBuilder("Successfully fetch order's detail from the database!", null, HttpStatus.OK, orderResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody OrderRequest orderRequest) {
        String message = orderService.create(orderRequest);
        return ResponseHandle.responseBuilder("Successfully create a new order!", null, HttpStatus.OK, message);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody OrderRequest orderRequest) {
        String message = orderService.update(orderRequest, id);
        return ResponseHandle.responseBuilder("Successfully update the order!", null, HttpStatus.OK, message);
    }

    @DeleteMapping("/delte/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        String message = orderService.delete(id);
        return ResponseHandle.responseBuilder("Successfully delete the order!", null, HttpStatus.OK, message);
    }
}
