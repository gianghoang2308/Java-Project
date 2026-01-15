package com.example.springapi09.service.Impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import com.example.springapi09.dto.BillingAddress.BillingAddressUpdate;
import jakarta.persistence.PersistenceException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapi09.dto.BillingAddress.BillingAddressRequest;
import com.example.springapi09.dto.BillingAddress.BillingAddressResponse;
import com.example.springapi09.entity.BillingAddress;
import com.example.springapi09.exception.NotFoundException;
import com.example.springapi09.mapper.BillingAddressMapper;
import com.example.springapi09.repository.BillingAddressRepository;
import com.example.springapi09.service.BillingAddressService;

@Service
public class BillingAddressServiceImpl implements BillingAddressService {

    @Autowired
    private BillingAddressRepository billingAddressRepository;

    @Autowired
    private BillingAddressMapper billingAddressMapper;

    @Override
    public List<BillingAddressResponse> getAllBillingAddresses() {
        List<BillingAddress> billingAddresses = billingAddressRepository.findAll();
        if (billingAddresses.isEmpty()) {
            throw new NotFoundException("Data is null");
        }
        return billingAddressMapper.toResponseList(billingAddresses);
    }

    @Override
    public BillingAddressResponse findById(Integer id) {
        Optional<BillingAddress> billingAddress = billingAddressRepository.findById(Objects.requireNonNull(id));
        if (billingAddress.isEmpty()) {
            throw new NotFoundException("Cannot found billingAddress with id: " + id);
        }

        return billingAddressMapper.toBillingAddressResponse(billingAddress.get());
    }

    @Override
    public String create(BillingAddressRequest billingAddressRequest) {
        if (billingAddressRequest == null) {
            throw new NotFoundException("Data cannot be null");
        }

        try {
            if (Boolean.FALSE.equals(billingAddressRequest.getShiptoDifferentAddress())) {
                billingAddressRequest.setShippingAddressFirstName(billingAddressRequest.getBillingAddressFirstName());
                billingAddressRequest.setShippingAddressLastName(billingAddressRequest.getBillingAddressLastName());
                billingAddressRequest.setShippingAddressEmail(billingAddressRequest.getBillingAddressEmail());
                billingAddressRequest.setShippingAddressAddress(billingAddressRequest.getBillingAddressAddress());
                billingAddressRequest.setShippingAddressCity(billingAddressRequest.getBillingAddressCity());
                billingAddressRequest.setShippingAddressCountry(billingAddressRequest.getBillingAddressCountry());
                billingAddressRequest.setShippingAddressZipCode(billingAddressRequest.getBillingAddressZipCode());
                billingAddressRequest.setShippingAddressTelephone(billingAddressRequest.getBillingAddressTelephone());
            }
            if (billingAddressRequest.getOrderNote() == null || billingAddressRequest.getOrderNote().trim().isEmpty()) {
                billingAddressRequest.setOrderNote("Empty");
            }
            System.out.println("Order Note before mapping: " + billingAddressRequest.getOrderNote());

            BillingAddress create = billingAddressMapper.toBillingAddress(billingAddressRequest);

            billingAddressRepository.save(Objects.requireNonNull(create));

            return "Successfully created new billing address: " + create;
        } catch (NotFoundException e) {
            return "Error: " + e.getMessage();
        } catch (IllegalArgumentException e) {
            return "Error: Invalid argument provided - " + e.getMessage();
        } catch (PersistenceException e) {
            return "Error: Database error occurred while saving the billing address - " + e.getMessage();
        } catch (Exception e) {
            return "An unexpected error occurred: " + e.getMessage();
        }

    }


    @Override
    public String update(BillingAddressUpdate billingAddressUpdate, Integer id) {
        if (billingAddressUpdate == null) {
            throw new NotFoundException("Data cannot be null");
        }

        BillingAddress existing = billingAddressRepository.findById(Objects.requireNonNull(id))
                .orElseThrow(() -> new NotFoundException("Cannot find billing address with id: " + id));

        try {
            updateFieldIfNotNull(billingAddressUpdate.getBillingAddressFirstName(), existing::setBillingAddressFirstName);
            updateFieldIfNotNull(billingAddressUpdate.getBillingAddressLastName(), existing::setBillingAddressLastName);
            updateFieldIfNotNull(billingAddressUpdate.getBillingAddressEmail(), existing::setBillingAddressEmail);
            updateFieldIfNotNull(billingAddressUpdate.getBillingAddressAddress(), existing::setBillingAddressAddress);
            updateFieldIfNotNull(billingAddressUpdate.getBillingAddressCity(), existing::setBillingAddressCity);
            updateFieldIfNotNull(billingAddressUpdate.getBillingAddressCountry(), existing::setBillingAddressCountry);
            updateFieldIfNotNull(billingAddressUpdate.getBillingAddressZipCode(), existing::setBillingAddressZipCode);
            updateFieldIfNotNull(billingAddressUpdate.getBillingAddressTelephone(), existing::setBillingAddressTelephone);

            if (billingAddressUpdate.getShiptoDifferentAddress() != null) {
                existing.setShiptoDifferentAddress(billingAddressUpdate.getShiptoDifferentAddress());
            }

            updateFieldIfNotNull(billingAddressUpdate.getShippingAddressFirstName(), existing::setShippingAddressFirstName);
            updateFieldIfNotNull(billingAddressUpdate.getShippingAddressLastName(), existing::setShippingAddressLastName);
            updateFieldIfNotNull(billingAddressUpdate.getShippingAddressEmail(), existing::setShippingAddressEmail);
            updateFieldIfNotNull(billingAddressUpdate.getShippingAddressAddress(), existing::setShippingAddressAddress);
            updateFieldIfNotNull(billingAddressUpdate.getShippingAddressCity(), existing::setShippingAddressCity);
            updateFieldIfNotNull(billingAddressUpdate.getShippingAddressCountry(), existing::setShippingAddressCountry);
            updateFieldIfNotNull(billingAddressUpdate.getShippingAddressZipCode(), existing::setShippingAddressZipCode);
            updateFieldIfNotNull(billingAddressUpdate.getShippingAddressTelephone(), existing::setShippingAddressTelephone);

            if (billingAddressUpdate.getOrderNote() != null && !billingAddressUpdate.getOrderNote().isEmpty()) {
                existing.setOrderNote(billingAddressUpdate.getOrderNote());
            }
        } catch (Exception e) {
            throw new RuntimeException("Update failed: " + e.getMessage());
        }

        billingAddressRepository.save(Objects.requireNonNull(existing));
        return "Successfully updated billing address with id: " + id;
    }

    private <T> void updateFieldIfNotNull(T value, Consumer<T> setter) {
        if (value != null && (value instanceof String && !((String) value).isEmpty() || !(value instanceof String))) {
            setter.accept(value);
        }
    }

    @Override
    public String delete(Integer id) {
        Optional<BillingAddress> billingAddress = billingAddressRepository.findById(Objects.requireNonNull(id));
        if (billingAddress.isEmpty()) {
            throw new NotFoundException("Cannot found billingAddress with id: " + id);
        }
        billingAddressRepository.deleteById(Objects.requireNonNull(id));
        return "Successfully delete billing address with id: " + id;
    }
}
