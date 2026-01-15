package com.example.springapi09.service;

import java.util.List;

import com.example.springapi09.dto.Role.RoleRequest;
import com.example.springapi09.dto.Role.RoleResponse;
import com.example.springapi09.dto.Role.RoleUpdate;

public interface RoleService {
    List<RoleResponse> getAllRoles();
    RoleResponse findById(Integer id);
    String create(RoleRequest roleRequest);
    String update(RoleUpdate roleUpdate, Integer id);
    String delete(Integer id);
}
