package com.example.springapi09.service;

import java.util.List;

import com.example.springapi09.dto.BillingAddress.BillingAddressRequest;
import com.example.springapi09.dto.BillingAddress.BillingAddressResponse;
import com.example.springapi09.dto.BillingAddress.BillingAddressUpdate;

public interface BillingAddressService {
    List<BillingAddressResponse> getAllBillingAddresses();
    BillingAddressResponse findById(Integer id);
    String create(BillingAddressRequest billingAddressRequest);
    String update(BillingAddressUpdate billingAddressUpdate, Integer id);
    String delete(Integer id);
}
