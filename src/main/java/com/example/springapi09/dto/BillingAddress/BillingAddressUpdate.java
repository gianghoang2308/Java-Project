package com.example.springapi09.dto.BillingAddress;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillingAddressUpdate {
    private String billingAddressFirstName;

    private String billingAddressLastName;

    @Email(message = "Kiem tra lai, phai nhap dung dinh dang email")
    private String billingAddressEmail;

    private String billingAddressAddress;

    private String billingAddressCity;

    private String billingAddressCountry;

    private String billingAddressZipCode;

    @Pattern(
            regexp = "^(0\\d{9}|\\+84\\d{9})$",
            message = "So dien thoai phai co 10 chu so va bat dau bang 0 hoac +84"
    )
    private String billingAddressTelephone;

    private Boolean shiptoDifferentAddress;

    private String shippingAddressFirstName;

    private String shippingAddressLastName;

    @Email(message = "Kiem tra lai dinh dang email")
    private String shippingAddressEmail;

    private String shippingAddressAddress;

    @NotNull(message = "Khong de trong ten thanh pho")
    private String shippingAddressCity;

    private String shippingAddressCountry;

    private String shippingAddressZipCode;

    @Pattern(
            regexp = "^(0\\d{9}|\\+84\\d{9})$",
            message = "So dien thoai phai co 10 chu so va bat dau bang 0 hoac +84"
    )
    private String shippingAddressTelephone;
    //text

    private String orderNote;
}
