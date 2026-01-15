package com.example.springapi09.service;

import java.util.List;

import com.example.springapi09.dto.User.UserRegisterUpdate;
import com.example.springapi09.dto.User.UserRequest;
import com.example.springapi09.dto.User.UserResponse;
import com.example.springapi09.dto.User.UserUpdate;
import com.example.springapi09.entity.User;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse findById(Integer id);
    String create(UserRequest user);
    String update(UserUpdate userUpdate, Integer id);
    String delete(Integer id);
//    User updateUser(Integer id, UserUpdate request);
}
