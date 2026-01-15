package com.example.springapi09.mapper;

import java.util.List;

import com.example.springapi09.dto.Role.RoleUpdate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.springapi09.dto.Role.RoleRequest;
import com.example.springapi09.dto.Role.RoleResponse;
import com.example.springapi09.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "admins", ignore = true)
    @Mapping(target = "users", ignore = true)
    Role toRole(RoleRequest roleRequest);

    RoleResponse toResponse(Role role);

    List<RoleResponse> toResponseList(List<Role> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "admins", ignore = true)
    @Mapping(target = "users", ignore = true)
    void updateEntity(RoleUpdate roleUpdate, @MappingTarget Role role);

}
