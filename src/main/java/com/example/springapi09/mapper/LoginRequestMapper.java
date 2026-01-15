package com.example.springapi09.mapper;


import com.example.springapi09.dto.Login.LoginResponse;
import com.example.springapi09.entity.Admin;
import com.example.springapi09.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginRequestMapper {


    LoginResponse toLoginResponse(Admin admin, String token);


    LoginResponse toLoginResponse(User user, String token);
}
