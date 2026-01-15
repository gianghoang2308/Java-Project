package com.example.springapi09.service.Impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapi09.dto.OrderDetail.OrderDetailRequest;
import com.example.springapi09.dto.OrderDetail.OrderDetailResponse;
import com.example.springapi09.entity.OrderDetail;
import com.example.springapi09.exception.NotFoundException;
import com.example.springapi09.mapper.OrderDetailMapper;
import com.example.springapi09.repository.OrderDetailRepository;
import com.example.springapi09.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Override
    public List<OrderDetailResponse> getAllOrderDetails() {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll();
        if (orderDetails.isEmpty()) {
            throw new NotFoundException("Data cannot be null!");
        }

        return orderDetailMapper.toResponseList(orderDetails);
    }

    @Override
    public OrderDetailResponse findById(Integer id) {
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(Objects.requireNonNull(id));
        if (orderDetail.isEmpty()) {
            throw new NotFoundException("Data cannot be null!");
        }
        return orderDetailMapper.toResponse(orderDetail.get());
    }

    @Override
    public String create(OrderDetailRequest orderDetailRequest) {
        if (orderDetailRequest == null) {
            throw new NotFoundException("Data cannot be null!");
        }
        OrderDetail create = orderDetailMapper.toOrderDetail(orderDetailRequest);
        orderDetailRepository.save(Objects.requireNonNull(create));
        return "Successfully create new order detail!";
    }

    @Override
    public String update(OrderDetailRequest orderDetailRequest, Integer id) {
        if (orderDetailRequest == null) {
            throw new NotFoundException("Data cannot be null!");
        }

         if (id == null) {
            throw new NotFoundException("Id cannot be null!");
        }

        OrderDetail existing = orderDetailRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cannot find order detail with id: " +id));

        orderDetailMapper.updateEntity(orderDetailRequest, existing);
        orderDetailRepository.save(Objects.requireNonNull(existing));
        return "Successfully update order detail with id: "+id;
    }

    @Override
    public String delete(Integer id) {
       Optional<OrderDetail> orderDetail = orderDetailRepository.findById(Objects.requireNonNull(id));
        if (orderDetail.isEmpty()) {
            throw new NotFoundException("Data cannot be null!");
        }
        orderDetailRepository.deleteById(id);
        return "Successfully delete order detail with id: "+id;
    }

}
