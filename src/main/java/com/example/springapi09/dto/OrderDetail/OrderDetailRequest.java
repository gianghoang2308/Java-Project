package com.example.springapi09.dto.OrderDetail;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailRequest {
    private Integer quantity;

    private Double unitPrice;

    private Double totalPrice;

    private Integer orderId;

    private Integer productId;
}
