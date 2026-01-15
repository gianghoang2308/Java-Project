package com.example.springapi09.dto.BillingAddress;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillingAddressResponse {

    private String billingAddressFirstName;
    private String billingAddressLastName;
    private String billingAddressEmail;
    private String billingAddressAddress;
    private String billingAddressCity;
    private String billingAddressCountry;
    private String billingAddressZipCode;
    private String billingAddressTelephone;
    private Boolean shiptoDifferentAddress;
    private String shippingAddressFirstName;
    private String shippingAddressLastName;
    private String shippingAddressEmail;
    private String shippingAddressAddress;
    private String shippingAddressCity;
    private String shippingAddressZipCode;
    private String shippingAddressTelephone;
    private String orderNote;
}
