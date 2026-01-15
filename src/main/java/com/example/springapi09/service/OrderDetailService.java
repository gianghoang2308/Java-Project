package com.example.springapi09.service;

import java.util.List;

import com.example.springapi09.dto.OrderDetail.OrderDetailRequest;
import com.example.springapi09.dto.OrderDetail.OrderDetailResponse;

public interface OrderDetailService {

    List<OrderDetailResponse> getAllOrderDetails();

    OrderDetailResponse findById(Integer id);

    String create(OrderDetailRequest orderDetailRequest);

    String update(OrderDetailRequest orderDetailRequest, Integer id);

    String delete(Integer id);
}
