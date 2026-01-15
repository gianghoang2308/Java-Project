package com.example.springapi09.service;

import java.util.List;

import com.example.springapi09.dto.Order.OrderRequest;
import com.example.springapi09.dto.Order.OrderResponse;

public interface OrderService {

    List<OrderResponse> getAllOrders();

    OrderResponse findById(Integer id);

    String create(OrderRequest orderRequest);

    String update(OrderRequest orderRequest, Integer id);

    String delete(Integer id);
}
