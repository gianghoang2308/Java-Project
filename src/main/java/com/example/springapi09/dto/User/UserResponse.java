package com.example.springapi09.dto.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.example.springapi09.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private String userName;

    private String password;

    private String fullName;
    //image
    private String avatar;

    private String phoneNumber;

    private String email;

    //image
    private String idCardFront;

    //image
    private String idCardBack;

    private LocalDate dateOfIssue;

    private String placeOfIssue;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private Integer roleId;
    private String roleName;
}
