package com.example.springapi09.mapper;

import java.util.List;

import com.example.springapi09.dto.Brand.BrandUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.springapi09.dto.Brand.BrandRequest;
import com.example.springapi09.dto.Brand.BrandResponse;
import com.example.springapi09.entity.Brand;

@Mapper(componentModel="spring")
public interface BrandMapper {

    @Mapping(target="id", ignore=true)
    @Mapping(target="logo", ignore=true)
    @Mapping(target="createTime", ignore=true)
    @Mapping(target="updateTime", ignore=true)
    @Mapping(target="products", ignore=true)
    Brand toBrand(BrandRequest brandRequest);

    BrandResponse toBrandResponse(Brand brand);

    List<BrandResponse> toResponseList(List<Brand> entities);

    @Mapping(target="id", ignore=true)
    @Mapping(target="logo", ignore=true)
    @Mapping(target="createTime", ignore=true)
    @Mapping(target="updateTime", ignore=true)
    @Mapping(target="products", ignore=true)
    void updateEntity(BrandUpdate brandUpdate, @MappingTarget Brand entity);

}
