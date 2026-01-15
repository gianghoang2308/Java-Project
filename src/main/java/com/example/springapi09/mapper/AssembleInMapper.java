package com.example.springapi09.mapper;

import java.util.List;

import com.example.springapi09.dto.AssembleIn.AssembleInUpdate;
import org.mapstruct.*;

import com.example.springapi09.dto.AssembleIn.AssembleInRequest;
import com.example.springapi09.dto.AssembleIn.AssembleInResponse;
import com.example.springapi09.entity.AssembleIn;

@Mapper(componentModel = "spring")
public interface AssembleInMapper {

    //Create
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "products", ignore = true)
    AssembleIn toAssembleIn(AssembleInRequest assembleInRequest);

    //Response
    AssembleInResponse toAssembleInResponse(AssembleIn assembleIn);

    //List mapping

    List<AssembleInResponse> toResponseList(List<AssembleIn> entities);

    //update
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "updateTime", ignore = true)
    @Mapping(target = "products", ignore = true)
    void updateEntity(AssembleInUpdate update, @MappingTarget AssembleIn entity);

}
