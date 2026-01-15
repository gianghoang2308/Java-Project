package com.example.springapi09.service.Impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import com.example.springapi09.dto.User.UserUpdate;

import com.example.springapi09.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapi09.dto.User.UserRequest;
import com.example.springapi09.dto.User.UserResponse;
import com.example.springapi09.entity.Role;
import com.example.springapi09.entity.User;
import com.example.springapi09.exception.NotFoundException;
import com.example.springapi09.mapper.UserMapper;
import com.example.springapi09.repository.RoleRepository;
import com.example.springapi09.repository.UserRepository;
import com.example.springapi09.service.UserService;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FileUploadService fileUploadService;

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new NotFoundException("User list is empty");
        }
        return userMapper.toResponseList(users);

    }

    @Override
    public UserResponse findById(Integer id) {

        Optional<User> user = userRepository.findById(Objects.requireNonNull(id));
        if (user.isEmpty()) {
            throw new NotFoundException("Cannot find an use with the id: " + id);

        }
        return userMapper.toUserResponse(user.get());
    }

    @Override
    public String create(UserRequest userRequest) {
        if (userRequest == null) {
            throw new NotFoundException("Data cannot be null!");
        }

        if (userRequest.getAvatar() == null || userRequest.getAvatar().isEmpty()
                || userRequest.getIdCardFront() == null || userRequest.getIdCardFront().isEmpty()
                || userRequest.getIdCardBack() == null || userRequest.getIdCardBack().isEmpty()) {
            throw new NotFoundException("Avatar, IdCardFront and IdCardBack need to be uploaded!");
        }

        User admin = userMapper.toUser(userRequest);
        admin.setUserId(UUID.randomUUID().toString());

//        if(userRequest.getPassword() == null || userRequest.getPassword().isEmpty()){
//            String hashedPassword = new BCryptPasswordEncoder().encode(userRequest.getPassword());
//            userRequest.setPassword(hashedPassword);
//        }

        try {
            admin.setAvatar(fileUploadService.upload(userRequest.getAvatar()));
            admin.setIdCardFront(fileUploadService.upload(userRequest.getIdCardFront()));
            admin.setIdCardBack(fileUploadService.upload(userRequest.getIdCardBack()));
        } catch (Exception e) {
            throw new RuntimeException("Upload file failed: " + e.getMessage());
        }

        Role roleDefault = roleRepository.findById(2)
                .orElseThrow(() -> new NotFoundException("Role id 2 not found"));
        admin.setRole(roleDefault);

        userRepository.save(admin);
        return "Successfully create a new user!";
    }

    @Override
    public String update(UserUpdate userUpdate, Integer id) {
        if (userUpdate == null) {
            throw new NotFoundException("Data cannot be null!");
        }

        if (id == null) {
            throw new NotFoundException("Id cannot be null!");
        }

        User existing = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cannot find an user with the id: " + id));

        try {
//            if (userUpdate.getPassword() != null && !userUpdate.getPassword().isEmpty()) {
//                String hashedPassword = new BCryptPasswordEncoder().encode(userUpdate.getPassword());
//                existing.setPassword(userUpdate.getPassword());
//            }

            if (userUpdate.getPassword() != null && !userUpdate.getPassword().isEmpty()) {
                existing.setPassword(userUpdate.getPassword());
            }


            if (userUpdate.getFullName() != null && !userUpdate.getFullName().isEmpty()) {
                existing.setFullName(userUpdate.getFullName());
            }

            if (StringUtils.hasText(userUpdate.getEmail())) {
                existing.setEmail(userUpdate.getEmail());
            }

            if (StringUtils.hasText(userUpdate.getPhoneNumber())) {
                existing.setPhoneNumber(userUpdate.getPhoneNumber());
            }

            if (userUpdate.getDateOfIssue() != null) {
                existing.setDateOfIssue(userUpdate.getDateOfIssue());
            }

            if (userUpdate.getPlaceOfIssue() != null && !userUpdate.getPlaceOfIssue().isEmpty()) {
                existing.setPlaceOfIssue(userUpdate.getPlaceOfIssue());
            }

            if (userUpdate.getAvatar() != null && !userUpdate.getAvatar().isEmpty()) {
                existing.setAvatar(fileUploadService.upload(userUpdate.getAvatar()));
            }

            if (userUpdate.getIdCardFront() != null && !userUpdate.getIdCardFront().isEmpty()) {
                existing.setIdCardFront(fileUploadService.upload(userUpdate.getIdCardFront()));
            }

            if (userUpdate.getIdCardBack() != null && !userUpdate.getIdCardBack().isEmpty()) {
                existing.setIdCardBack(fileUploadService.upload(userUpdate.getIdCardBack()));
            }

        } catch (Exception e) {
            throw new RuntimeException("Upload file failed: " + e.getMessage());
        }

        userRepository.save(Objects.requireNonNull(existing));

        return "Successfully updated an user with the id: " + id;
    }

    @Override
    public String delete(Integer id) {
        Optional<User> user = userRepository.findById(Objects.requireNonNull(id));
        if (user.isEmpty()) {
            throw new NotFoundException("Cannot find an user with the id: " + id);
        }
        userRepository.deleteById(id);
        return "Successfully delete an user with the id: " + id;

    }

}