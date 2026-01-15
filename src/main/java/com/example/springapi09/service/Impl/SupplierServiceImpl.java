package com.example.springapi09.service.Impl;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


import com.example.springapi09.dto.Supplier.SupplierUpdate;
import com.example.springapi09.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapi09.dto.Supplier.SupplierRequest;
import com.example.springapi09.dto.Supplier.SupplierResponse;
import com.example.springapi09.entity.Supplier;
import com.example.springapi09.exception.NotFoundException;
import com.example.springapi09.mapper.SupplierMapper;
import com.example.springapi09.repository.SupplierRepository;
import com.example.springapi09.service.SupplierService;
import org.springframework.util.StringUtils;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private FileUploadService fileUploadService;

    @Override
    public List<SupplierResponse> getAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        if (suppliers.isEmpty()) {
            throw new NotFoundException("Data cannot be null!");
        }
        return supplierMapper.toResponseList(suppliers);
    }

    @Override
    public SupplierResponse findById(Integer id) {
        Optional<Supplier> supplier = supplierRepository.findById(Objects.requireNonNull(id));
        if (supplier.isEmpty()) {
            throw new NotFoundException("Data cannot be null!");
        }
        return supplierMapper.toResponse(supplier.get());
    }

    @Override
    public String create(SupplierRequest supplierRequest) {
        if (supplierRequest == null) {
            throw new NotFoundException("Data cannot be null!");
        }

        String logoFilePath = null;

        try {
            logoFilePath = fileUploadService.upload(supplierRequest.getLogo());
        } catch (IOException e) {
            throw new RuntimeException("Upload file failed: " + e.getMessage());
        }

        Supplier create = supplierMapper.toSupplier(supplierRequest);
        create.setLogo(logoFilePath);

        supplierRepository.save(Objects.requireNonNull(create));

        return "Successfully created new brand with name: " + create.getName();
    }

    @Override
    public String update(SupplierUpdate supplierUpdate, Integer id) {
        if (supplierUpdate == null) {
            throw new NotFoundException("Data cannot be null!");
        }

        if (id == null) {
            throw new NotFoundException("Id cannot be null!");
        }

        Supplier existing = supplierRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cannot find brand with id: " + id));

        try {
            if (supplierUpdate.getName() != null && !supplierUpdate.getName().isEmpty()) {
                existing.setName(supplierUpdate.getName());
            }

            if (supplierUpdate.getAddress() != null && !supplierUpdate.getAddress().isEmpty()) {
                existing.setAddress(supplierUpdate.getAddress());
            }

            if (StringUtils.hasText(supplierUpdate.getEmail())) {
                existing.setEmail(supplierUpdate.getEmail());
            }

            if (StringUtils.hasText(supplierUpdate.getPhoneNumber())) {
                existing.setPhoneNumber(supplierUpdate.getPhoneNumber());
            }

            if (supplierUpdate.getTaxCode() != null && !supplierUpdate.getTaxCode().isEmpty()) {
                existing.setTaxCode(supplierUpdate.getTaxCode());
            }

            if (supplierUpdate.getWebsite() != null && !supplierUpdate.getWebsite().isEmpty()) {
                existing.setWebsite(supplierUpdate.getWebsite());
            }

            if (supplierUpdate.getLogo() != null && !supplierUpdate.getLogo().isEmpty()) {
                existing.setLogo(fileUploadService.upload(supplierUpdate.getLogo()));
            }

        } catch (Exception e) {
            throw new RuntimeException("Upload file failed: " + e.getMessage());
        }

        supplierRepository.save(Objects.requireNonNull(existing));

        return "Successfully updated brand with id: " + id;
    }

    @Override
    public String delete(Integer id) {
        Optional<Supplier> supplier = supplierRepository.findById(Objects.requireNonNull(id));
        if (supplier.isEmpty()) {
            throw new NotFoundException("Data cannot be null!");
        }
        supplierRepository.deleteById(id);
        return "Successfully delete supplier with id: " + id;
    }

}
