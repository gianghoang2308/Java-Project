package com.example.springapi09.service.Impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


import com.example.springapi09.dto.admin.AdminRegisterUpdate;
import com.example.springapi09.dto.admin.AdminUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springapi09.dto.admin.AdminRequest;
import com.example.springapi09.dto.admin.AdminResponse;
import com.example.springapi09.entity.Admin;
import com.example.springapi09.entity.Role;
import com.example.springapi09.exception.NotFoundException;
import com.example.springapi09.mapper.AdminMapper;
import com.example.springapi09.repository.AdminRepository;
import com.example.springapi09.repository.RoleRepository;
import com.example.springapi09.service.AdminService;
import com.example.springapi09.service.FileUploadService;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private FileUploadService fileUploadService;

    private final PasswordEncoder passwordEncoder;


    @Override
    public List<AdminResponse> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        if (admins.isEmpty()) {
            throw new NotFoundException("Admin list is empty");
        }
        return adminMapper.toResponseList(admins);

    }

    @Override
    public AdminResponse findById(Integer id) {

        Optional<Admin> admin = adminRepository.findById(Objects.requireNonNull(id));
        if (admin.isEmpty()) {
            throw new NotFoundException("Cannot find assembleIn with id: " + id);

        }
        return adminMapper.toAdminResponse(admin.get());
    }

    @Override
    public String create(AdminRequest adminRequest) {
        if (adminRequest == null) {
            throw new NotFoundException("Data cannot be null!");
        }

        if (adminRequest.getAvatar() == null || adminRequest.getAvatar().isEmpty()
                || adminRequest.getIdCardFront() == null || adminRequest.getIdCardFront().isEmpty()
                || adminRequest.getIdCardBack() == null || adminRequest.getIdCardBack().isEmpty()) {
            throw new NotFoundException("Avatar, IdCardFront and IdCardBack need to be uploaded!");
        }

        Admin admin = adminMapper.toAdmin(adminRequest);
        admin.setAdminId(UUID.randomUUID().toString());

//        if(adminRequest.getPassword() != null && !adminRequest.getPassword().isEmpty()) {
//            String hashedPassword = new BCryptPasswordEncoder().encode(adminRequest.getPassword());
//            admin.setPassword(hashedPassword);
//        }

        try {
            admin.setAvatar(fileUploadService.upload(adminRequest.getAvatar()));
            admin.setIdCardFront(fileUploadService.upload(adminRequest.getIdCardFront()));
            admin.setIdCardBack(fileUploadService.upload(adminRequest.getIdCardBack()));
        } catch (Exception e) {
            throw new RuntimeException("Upload file failed: " + e.getMessage());
        }

        Role roleDefault = roleRepository.findById(1)
                .orElseThrow(() -> new NotFoundException("Role id 1 not found"));
        admin.setRole(roleDefault);

        adminRepository.save(admin);
        return "Successfully create new admin!";
    }

    @Override
    public String update(AdminUpdate adminUpdate, Integer id) {
        if (adminUpdate == null) {
            throw new NotFoundException("Data cannot be null!");
        }

        if (id == null) {
            throw new NotFoundException("Id cannot be null!");
        }

        Admin existing = adminRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cannot find brand with id: " + id));

        try {
            if (adminUpdate.getPassword() != null && !adminUpdate.getPassword().isEmpty()) {
                existing.setPassword(adminUpdate.getPassword());
            }

            if (adminUpdate.getFullName() != null && !adminUpdate.getFullName().isEmpty()) {
                existing.setFullName(adminUpdate.getFullName());
            }

            if (StringUtils.hasText(adminUpdate.getEmail())) {
                existing.setEmail(adminUpdate.getEmail());
            }

            if (StringUtils.hasText(adminUpdate.getPhoneNumber())) {
                existing.setPhoneNumber(adminUpdate.getPhoneNumber());
            }

            if (adminUpdate.getDateOfIssue() != null) {
                existing.setDateOfIssue(adminUpdate.getDateOfIssue());
            }

            if (adminUpdate.getPlaceOfIssue() != null && !adminUpdate.getPlaceOfIssue().isEmpty()) {
                existing.setPlaceOfIssue(adminUpdate.getPlaceOfIssue());
            }

            if (adminUpdate.getAvatar() != null && !adminUpdate.getAvatar().isEmpty()) {
                existing.setAvatar(fileUploadService.upload(adminUpdate.getAvatar()));
            }

            if (adminUpdate.getIdCardFront() != null && !adminUpdate.getIdCardFront().isEmpty()) {
                existing.setIdCardFront(fileUploadService.upload(adminUpdate.getIdCardFront()));
            }

            if (adminUpdate.getIdCardBack() != null && !adminUpdate.getIdCardBack().isEmpty()) {
                existing.setIdCardBack(fileUploadService.upload(adminUpdate.getIdCardBack()));
            }

        } catch (Exception e) {
            throw new RuntimeException("Upload file failed: " + e.getMessage());
        }

        adminRepository.save(Objects.requireNonNull(existing));

        return "Successfully updated brand with id: " + id;
    }

    @Override
    public String delete(Integer id) {
        Optional<Admin> admin = adminRepository.findById(Objects.requireNonNull(id));
        if (admin.isEmpty()) {
            throw new NotFoundException("Cannot find admin with id: " + id);
        }
        adminRepository.deleteById(id);
        return "Successfully delete admin with id: " + id;

    }

    @Override
    public Admin updateAdmin(Integer id, AdminRegisterUpdate request) {

        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            admin.setPassword(passwordEncoder.encode(request.getPassword()));
        }


        if (request.getFullName() != null && !request.getFullName().isBlank()) {
            admin.setFullName(request.getFullName());
        }

        if (request.getPhoneNumber() != null) {
            if (!request.getPhoneNumber().matches("^0[3|5|7|8|9][0-9]{8}$")) {
                throw new IllegalArgumentException("So dien thoai khong dung dinh dang Viet Nam");
            }
            admin.setPhoneNumber(request.getPhoneNumber());
        }

        if (request.getEmail() != null) {
            if (!request.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                throw new IllegalArgumentException("Email khong dung dinh dang");
            }
            admin.setEmail(request.getEmail());
        }

        if (request.getDateOfIssue() != null) {
            admin.setDateOfIssue(request.getDateOfIssue());
        }

        if (request.getPlaceOfIssue() != null && !request.getPlaceOfIssue().isBlank()) {
            admin.setPlaceOfIssue(request.getPlaceOfIssue());
        }

        MultipartFile avatar = request.getAvatar();
        if (avatar != null && !avatar.isEmpty()) {
            try {
                String avatarName = fileUploadService.upload(avatar);
                admin.setAvatar(avatarName);
            } catch (Exception e) {
                throw new RuntimeException("Upload avatar that bai");
            }
        }

        MultipartFile idCardFront = request.getIdCardFront();
        if (idCardFront != null && !idCardFront.isEmpty()) {
            try {
                String frontName = fileUploadService.upload(idCardFront);
                admin.setIdCardFront(frontName);
            } catch (Exception e) {
                throw new RuntimeException("Upload CCCD mat truoc that bai");
            }
        }

        MultipartFile idCardBack = request.getIdCardBack();
        if (idCardBack != null && !idCardBack.isEmpty()) {
            try {
                String backName = fileUploadService.upload(idCardBack);
                admin.setIdCardBack(backName);
            } catch (Exception e) {
                throw new RuntimeException("Upload CCCD mat sau that bai");
            }
        }

        return adminRepository.save(admin);
    }


}
