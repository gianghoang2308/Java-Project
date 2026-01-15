package com.example.springapi09.mapper;

import java.util.List;

import com.example.springapi09.dto.Supplier.SupplierUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.springapi09.dto.Supplier.SupplierRequest;
import com.example.springapi09.dto.Supplier.SupplierResponse;
import com.example.springapi09.entity.Supplier;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "logo", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "products", ignore = true)
    Supplier toSupplier(SupplierRequest supplierRequest);

    SupplierResponse toResponse(Supplier supplier);

    List<SupplierResponse> toResponseList(List<Supplier> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "logo", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "products", ignore = true)
    void updateEntity(SupplierUpdate supplierUpdate, @MappingTarget Supplier entity);

}
