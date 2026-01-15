package com.example.springapi09.service;

import java.util.List;

import com.example.springapi09.dto.Supplier.SupplierRequest;
import com.example.springapi09.dto.Supplier.SupplierResponse;
import com.example.springapi09.dto.Supplier.SupplierUpdate;

public interface SupplierService {
    List<SupplierResponse> getAllSuppliers();
    SupplierResponse findById(Integer id);
    String create(SupplierRequest supplierRequest);
    String update(SupplierUpdate supplierUpdate, Integer id);
    String delete(Integer id);
}
