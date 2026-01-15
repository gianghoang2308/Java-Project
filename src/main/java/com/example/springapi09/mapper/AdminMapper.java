package com.example.springapi09.mapper;

import java.util.List;

import com.example.springapi09.dto.admin.AdminUpdate;
import org.mapstruct.*;

import com.example.springapi09.dto.admin.AdminRequest;
import com.example.springapi09.dto.admin.AdminResponse;
import com.example.springapi09.entity.Admin;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    @Mapping(source = "accountName", target = "name")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "adminId", ignore = true)
    @Mapping(target = "avatar", ignore = true)
    @Mapping(target = "idCardFront", ignore = true)
    @Mapping(target = "idCardBack", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "role", ignore = true)
    Admin toAdmin(AdminRequest request);;

    @Mapping(source = "name", target = "accountName")
    @Mapping(source = "role.id", target = "roleId")
    @Mapping(source = "role.name", target = "roleName")
    AdminResponse toAdminResponse(Admin admin);

    List<AdminResponse> toResponseList(List<Admin> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "adminId", ignore = true)
    @Mapping(target = "avatar", ignore = true)
    @Mapping(target = "idCardFront", ignore = true)
    @Mapping(target = "idCardBack", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "role", ignore = true)
    void updateEntity(AdminUpdate adminUpdate, @MappingTarget Admin entity);

}
