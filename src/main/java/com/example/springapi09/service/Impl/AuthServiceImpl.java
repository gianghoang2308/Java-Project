package com.example.springapi09.service.Impl;


import com.example.springapi09.dto.Login.LoginRequest;
import com.example.springapi09.entity.Admin;
import com.example.springapi09.entity.Role;
import com.example.springapi09.entity.User;
import com.example.springapi09.repository.AdminRepository;
import com.example.springapi09.repository.RoleRepository;
import com.example.springapi09.repository.UserRepository;
import com.example.springapi09.security.JWTUtil;
import com.example.springapi09.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    @Override
    public String loginUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getName(),
                        loginRequest.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtil.generateToken(userDetails);
    }

    @Override
    public String registerUser(LoginRequest loginRequest) {
        if (userRepository.existsByName(loginRequest.getName())) {
            throw new RuntimeException("User name already exists");
        }

        Role userRole = roleRepository.findByName("User")
                .orElseThrow(() -> new RuntimeException("Role user not found"));

        User user = new User();
        user.setName(loginRequest.getName());
        user.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
        user.setRole(userRole);
        user.setUserId(UUID.randomUUID().toString());
        user.setFullName("");
        user.setAvatar("");
        user.setPhoneNumber("");
        user.setEmail("");
        user.setIdCardFront("");
        user.setIdCardBack("");
        user.setDateOfIssue(LocalDate.now());
        user.setPlaceOfIssue("");
        userRepository.save(user);

        return "Successfully register a new user!";
    }

    @Override
    public String registerAdmin(LoginRequest loginRequest) {
        if (adminRepository.existsByName(loginRequest.getName())) {
            throw new RuntimeException("Admin already exists with name " + loginRequest.getName());
        }

        Role adminRole = roleRepository.findByName("Admin")
                .orElseThrow(() -> new RuntimeException("Role admin not found"));

        Admin admin = new Admin();
        admin.setName(loginRequest.getName());
        admin.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
        admin.setRole(adminRole);
        admin.setAdminId(UUID.randomUUID().toString());
        admin.setFullName("");
        admin.setAvatar("");
        admin.setPhoneNumber("");
        admin.setEmail("");
        admin.setIdCardFront("");
        admin.setIdCardBack("");
        admin.setDateOfIssue(LocalDate.now());
        admin.setPlaceOfIssue("");

        adminRepository.save(admin);
        return "Successfully register a new admin!";
    }
}

