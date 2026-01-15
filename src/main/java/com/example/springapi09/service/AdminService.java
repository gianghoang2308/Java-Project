package com.example.springapi09.service;

import java.util.List;

import com.example.springapi09.dto.admin.AdminRegisterUpdate;
import com.example.springapi09.dto.admin.AdminRequest;
import com.example.springapi09.dto.admin.AdminResponse;
import com.example.springapi09.dto.admin.AdminUpdate;
import com.example.springapi09.entity.Admin;

public interface AdminService {
    List<AdminResponse> getAllAdmins();
    AdminResponse findById(Integer id);
    String create(AdminRequest adminRequest);
    String update(AdminUpdate adminUpdate, Integer id);
    String delete(Integer integer);
    Admin updateAdmin(Integer id, AdminRegisterUpdate request);
}
