package com.example.springapi09.dto.Order;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderTime;

    private Integer paymentMethod;

    private Integer totalAmount;

    private Double shippingFee;

    private Boolean isTermAccepted;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer userId;

    private String userName;
}
