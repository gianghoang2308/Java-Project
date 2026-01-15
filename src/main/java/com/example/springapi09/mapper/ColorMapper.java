package com.example.springapi09.mapper;

import java.util.List;

import com.example.springapi09.dto.Color.ColorUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.springapi09.dto.Color.ColorRequest;
import com.example.springapi09.dto.Color.ColorResponse;
import com.example.springapi09.entity.Color;

@Mapper(componentModel="spring")
public interface ColorMapper {

    @Mapping(target="id", ignore=true)
    @Mapping(target="createTime", ignore=true)
    @Mapping(target="updateTime", ignore=true)
    @Mapping(target="products",ignore=true)
    Color toColor(ColorRequest colorRequest);

    ColorResponse toColorResponse(Color color);

    List<ColorResponse> toResponseList(List<Color> entities);

    @Mapping(target="id", ignore=true)
    @Mapping(target="createTime", ignore=true)
    @Mapping(target="updateTime", ignore=true)
    @Mapping(target="products",ignore=true)
    void updateEntity(ColorUpdate colorUpdate, @MappingTarget Color entity);
    
}
