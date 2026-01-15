package com.example.springapi09.service.Impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapi09.dto.Order.OrderRequest;
import com.example.springapi09.dto.Order.OrderResponse;
import com.example.springapi09.entity.Order;
import com.example.springapi09.exception.NotFoundException;
import com.example.springapi09.mapper.OrderMapper;
import com.example.springapi09.repository.OrderRepository;
import com.example.springapi09.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderMapper orderMapper;


    @Override
    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            throw new NotFoundException("Data cannot be null!");
        }
        return orderMapper.toResponseList(orders);
    }

    @Override
    public OrderResponse findById(Integer id) {
        Optional<Order> order = orderRepository.findById(Objects.requireNonNull(id));
        if (order.isEmpty()) {
            throw new NotFoundException("Data cannot be null!");
        }
        return orderMapper.toResponse(order.get());
    }

    @Override
    public String create(OrderRequest orderRequest) {
        if (orderRequest == null) {
            throw new NotFoundException("Data cannot be null!");
        }
        Order create = orderMapper.toOrder(orderRequest);
        orderRepository.save(Objects.requireNonNull(create));
        return "Successfully create new order!";
    }

    @Override
    public String update(OrderRequest orderRequest, Integer id) {
        if (orderRequest == null ) {
            throw new NotFoundException("Data cannot be null!");
        }

       Order existing = orderRepository.findById(Objects.requireNonNull(id))
       .orElseThrow(() -> new NotFoundException("Cannot find category with id: "+id));
        orderMapper.updateEntity(orderRequest, existing);
        orderRepository.save(Objects.requireNonNull(existing));
        return "Successfully update order with id:"+id;
    }

    @Override
    public String delete(Integer id) {
        Optional<Order> order = orderRepository.findById(Objects.requireNonNull(id));
        if (order.isEmpty()) {
            throw new NotFoundException("Data cannot be null!");
        }
        orderRepository.deleteById(id);
        return "Successfully delete order with id: "+id;
    }
}
