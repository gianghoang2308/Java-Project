package com.example.springapi09.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.springapi09.dto.Product.ProductRequest;
import com.example.springapi09.dto.Product.ProductResponse;
import com.example.springapi09.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "mainImage", ignore = true)
    Product toProduct(ProductRequest productRequest);

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "brandId", source = "brand.id")
    @Mapping(target = "supplierId", source = "supplier.id")
    @Mapping(target = "assembleInId", source = "assembleIn.id")
    @Mapping(target = "colorId", source = "color.id")
    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "brandName", source = "brand.name")
    @Mapping(target = "supplierName", source = "supplier.name")
    @Mapping(target = "assembleInCountry", source = "assembleIn.country")
    @Mapping(target = "colorName", source = "color.name")
    ProductResponse toResponse(Product product);

    List<ProductResponse> toResponseList(List<Product> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "mainImage", ignore = true)
    void updateEntity(ProductRequest productRequest, @MappingTarget Product product);
}
