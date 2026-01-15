package com.example.springapi09.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.springapi09.dto.Order.OrderRequest;
import com.example.springapi09.dto.Order.OrderResponse;
import com.example.springapi09.entity.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderTime", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    Order toOrder(OrderRequest orderRequest);

    OrderResponse toResponse(Order order);

    List<OrderResponse> toResponseList(List<Order> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderTime", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    void updateEntity(OrderRequest orderRequest, @MappingTarget Order order);

}
