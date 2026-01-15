package com.example.springapi09.mapper;

import java.util.List;

import com.example.springapi09.dto.Category.CategoryUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.springapi09.dto.Category.CategoryRequest;
import com.example.springapi09.dto.Category.CategoryResponse;
import com.example.springapi09.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "products", ignore = true)
    Category toCategory(CategoryRequest request);

    CategoryResponse toCategoryResponse(Category category);

    List<CategoryResponse> toResponseList(List<Category> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "products", ignore = true)
    void updateEntity(CategoryUpdate categoryUpdate, @MappingTarget Category entity);

}
