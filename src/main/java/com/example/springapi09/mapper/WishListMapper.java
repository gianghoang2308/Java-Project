package com.example.springapi09.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.springapi09.dto.WishList.WishListRequest;
import com.example.springapi09.dto.WishList.WishListResponse;
import com.example.springapi09.entity.WishList;

@Mapper(componentModel = "spring")
public interface WishListMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    WishList toWishList(WishListRequest wishListRequest);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "userName", source = "user.name")
    @Mapping(target = "productName", source = "product.name")
    WishListResponse toResponse(WishList wishList);

    List<WishListResponse> toResponseList(List<WishList> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    void updateEntity(WishListRequest wishListRequest, @MappingTarget WishList wishList);

}
