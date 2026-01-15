package com.example.springapi09.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.springapi09.dto.OrderDetail.OrderDetailRequest;
import com.example.springapi09.dto.OrderDetail.OrderDetailResponse;
import com.example.springapi09.entity.OrderDetail;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {

    // ===== CREATE: Request -> Entity =====
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "order", ignore = true)     
    @Mapping(target = "product", ignore = true)   
    OrderDetail toOrderDetail(OrderDetailRequest request);

    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName") 
    OrderDetailResponse toResponse(OrderDetail orderDetail);

    List<OrderDetailResponse> toResponseList(List<OrderDetail> entities);

    // ===== UPDATE =====
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "product", ignore = true)
    void updateEntity(OrderDetailRequest request, @MappingTarget OrderDetail entity);
}
