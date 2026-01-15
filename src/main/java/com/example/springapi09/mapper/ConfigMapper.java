package com.example.springapi09.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.springapi09.dto.Config.ConfigRequest;
import com.example.springapi09.dto.Config.ConfigResponse;
import com.example.springapi09.entity.Config;

@Mapper(componentModel = "spring")
public interface ConfigMapper {

    @Mapping(target = "id", ignore = true)
    Config toConfig(ConfigRequest configRequest);

    ConfigResponse toResponse(Config config);

    List<ConfigResponse> toResponseList(List<Config> entities);

    @Mapping(target = "id", ignore = true)
    void updateEntity(ConfigRequest configRequest, @MappingTarget Config entity);

}
