package com.example.springapi09.dto.admin;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminResponse {

    private String adminId;

    private String accountName;
    private String fullName;
    private String phoneNumber;
    private String email;

    private String avatar;
    private String idCardFront;
    private String idCardBack;

    private LocalDate dateOfIssue;
    private String placeOfIssue;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    private Integer roleId;
    private String roleName;
}
