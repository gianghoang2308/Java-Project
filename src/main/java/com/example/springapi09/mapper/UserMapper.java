package com.example.springapi09.mapper;

import java.util.List;

import com.example.springapi09.dto.User.UserUpdate;
import org.mapstruct.*;

import com.example.springapi09.dto.User.UserRequest;
import com.example.springapi09.dto.User.UserResponse;
import com.example.springapi09.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "userName", target = "name")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "avatar", ignore = true)
    @Mapping(target = "idCardFront", ignore = true)
    @Mapping(target = "idCardBack", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toUser(UserRequest request);;

    @Mapping(source = "name", target = "userName")
    @Mapping(source = "role.id", target = "roleId")
    @Mapping(source = "role.name", target = "roleName")
    UserResponse toUserResponse(User admin);

    List<UserResponse> toResponseList(List<User> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "avatar", ignore = true)
    @Mapping(target = "idCardFront", ignore = true)
    @Mapping(target = "idCardBack", ignore = true)
    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "role", ignore = true)
    void updateEntity(UserUpdate adminUpdate, @MappingTarget User entity);
}
