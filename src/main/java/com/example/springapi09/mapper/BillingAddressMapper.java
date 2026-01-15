package com.example.springapi09.mapper;

import java.util.List;

import com.example.springapi09.dto.BillingAddress.BillingAddressUpdate;
import org.mapstruct.*;

import com.example.springapi09.dto.BillingAddress.BillingAddressRequest;
import com.example.springapi09.dto.BillingAddress.BillingAddressResponse;
import com.example.springapi09.entity.BillingAddress;

@Mapper(componentModel="spring")
public interface BillingAddressMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target =  "orderNote", source="orderNote")
    BillingAddress toBillingAddress(BillingAddressRequest billingAddressRequest);

    BillingAddressResponse toBillingAddressResponse(BillingAddress billingAddress);

    List<BillingAddressResponse> toResponseList(List<BillingAddress> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void updateEntity(BillingAddressUpdate request, @MappingTarget BillingAddress entity);

}
