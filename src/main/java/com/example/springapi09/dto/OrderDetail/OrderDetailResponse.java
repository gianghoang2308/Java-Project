package com.example.springapi09.dto.OrderDetail;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailResponse {
    private Integer quantity;

    private Double unitPrice;

    private Double totalPrice;


    private LocalDateTime createTime;


    private LocalDateTime updateTime;

    private Integer orderId;

    private Integer productId;
    private String productName;
}
