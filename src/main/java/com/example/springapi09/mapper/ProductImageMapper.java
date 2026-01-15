package com.example.springapi09.mapper;

import java.util.List;

import com.example.springapi09.dto.ProductImage.ProductImageUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.springapi09.dto.ProductImage.ProductImageRequest;
import com.example.springapi09.dto.ProductImage.ProductImageResponse;
import com.example.springapi09.entity.ProductImage;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProductImageMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "images", ignore = true)
    ProductImage toProduct(ProductImageRequest request);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    ProductImageResponse toResponse(ProductImage productImage);


    List<ProductImageResponse> toResponseList(List<ProductImage> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "images", ignore = true)
    void updateEntity(ProductImageUpdate productImageUpdate, @MappingTarget ProductImage entity);

    @Named("mapImagesToString")
    default String mapImagesToString(List<String> images) {
        if (images == null || images.isEmpty()) {
            return "";
        }
        return String.join(",", images); // Convert list to comma-separated string
    }
}
