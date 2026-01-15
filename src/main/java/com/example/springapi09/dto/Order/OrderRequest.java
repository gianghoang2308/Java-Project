package com.example.springapi09.dto.Order;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    /*
     1. Cash
     2. CreditCard
     3. BankTransfer
     4. QRCode
    */
    @NotNull(message = "Phuong thuc thanh toan khong duoc de trong")
    private Integer paymentMethod;

    @NotNull(message = "Tong tien khong duoc de trong")
    private Integer totalAmount;

    private Double shippingFee;

    @NotNull(message = "Ban phai dong y dieu khoan")
    private Boolean isTermAccepted;

    private Integer userId;
}
