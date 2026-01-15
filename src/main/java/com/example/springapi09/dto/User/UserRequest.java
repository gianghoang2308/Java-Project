package com.example.springapi09.dto.User;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotNull(message = "Ten tai khoan khong duoc de trong")
    private String userName;

    @NotNull(message = "Password khong duoc de trong")
    @Pattern(regexp = "^(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$",
            message = "Password phai co it nhat 8 ky tu va chua 1 ky tu dac biet"
    )
    private String password;

    @NotNull(message = "Ten khong duoc de trong")
    private String fullName;

    private MultipartFile avatar;

    @NotNull(message = "So dien thoai khong de trong")
    @Pattern(
            regexp = "^(0\\d{9}|\\+84\\d{9})$",
            message = "So dien thoai phai co 10 chu so va bat dau bang 0 hoac +84"
    )
    private String phoneNumber;

    @NotNull(message = "Email khong duoc de")
    @Email(message = "Email khong dung dinh dang")
    private String email;

    //image
    private MultipartFile idCardFront;

    //image
    private MultipartFile idCardBack;

    @NotNull(message = "Thoi gian cap khong duoc de trong")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfIssue;

    @NotNull(message = "Ten noi cap khong duoc de trong")
    private String placeOfIssue;

 
}
