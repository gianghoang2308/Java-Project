package com.example.springapi09.dto.admin;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminRequest {

    @NotBlank(message = "Ten tai khoan khong duoc de trong")
    private String accountName;

    @NotBlank(message = "Password khong duoc de trong")
    @Pattern(
            regexp = "^(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$",
            message = "Password phai co it nhat 8 ky tu va chua 1 ky tu dac biet"
    )
    private String password;

    @NotBlank(message = "Ten khong duoc de trong")
    private String fullName;

    @NotBlank(message = "So dien thoai khong duoc de trong")
    @Pattern(
            regexp = "^(0\\d{9}|\\+84\\d{9})$",
            message = "So dien thoai phai co 10 chu so va bat dau bang 0 hoac +84"
    )
    private String phoneNumber;

    @NotBlank(message = "Email khong duoc de trong")
    @Email(message = "Email khong dung dinh dang")
    private String email;

    @NotNull(message = "Thoi gian cap khong duoc de trong")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfIssue;

    @NotBlank(message = "Noi cap khong duoc de trong")
    private String placeOfIssue;

    @NotNull(message = "Avatar khong duoc de trong")
    private MultipartFile avatar;

    @NotNull(message = "Anh CCCD khong duoc de trong")
    private MultipartFile idCardFront;

    @NotNull(message = "Anh CCCD khong duoc de trong")
    private MultipartFile idCardBack;

}
