package com.example.springapi09.dto.BillingAddress;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillingAddressRequest {

    @NotBlank(message = "Khong de trong first_name")
    private String billingAddressFirstName;

    @NotBlank(message = "Khong de trong last_name")
    private String billingAddressLastName;

    @NotBlank(message = "Khong de trong email")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+)$", message = "Kiem tra lai, phai nhap dung dinh dang email")
    private String billingAddressEmail;

    @NotBlank(message = "Khong de trong dia chi")
    private String billingAddressAddress;

    @NotBlank(message = "Khong de trong ten thanh pho")
    private String billingAddressCity;

    @NotBlank(message = "Khong de trong ten quoc gia")
    private String billingAddressCountry;

    @NotBlank(message = "Khong de trong ma buu dien")
    private String billingAddressZipCode;

    @NotBlank(message = "So dien thoai khong de trong")
    @Pattern(
            regexp = "^(0\\d{9}|\\+84\\d{9})$",
            message = "So dien thoai phai co 10 chu so va bat dau bang 0 hoac +84"
    )
    private String billingAddressTelephone;

    @NotNull(message = "Cau hoi: Giao hang den dia chi khac?")
    private Boolean shiptoDifferentAddress;

    private String shippingAddressFirstName;

    private String shippingAddressLastName;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+)$", message = "Kiem tra lai, phai nhap dung dinh dang email")
    private String shippingAddressEmail;

    private String shippingAddressAddress;

    private String shippingAddressCity;

    private String shippingAddressCountry;

    private String shippingAddressZipCode;

    @Pattern(
            regexp = "^(0\\d{9}|\\+84\\d{9})$",
            message = "So dien thoai phai co 10 chu so va bat dau bang 0 hoac +84"
    )
    private String shippingAddressTelephone;

    @NotBlank(message = "Khong de trong phan ghi chu")
    private String orderNote;
}
