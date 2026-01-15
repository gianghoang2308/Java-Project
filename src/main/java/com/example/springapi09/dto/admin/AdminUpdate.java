package com.example.springapi09.dto.admin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUpdate {

    @Pattern(
            regexp = "^(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$",
            message = "Password phai co it nhat 8 ky tu va chua 1 ky tu dac biet"
    )
    private String password;

    private String fullName;

    @Pattern(
            regexp = "^$|^(0\\d{9}|\\+84\\d{9})$",
            message = "So dien thoai phai co 10 chu so va bat dau bang 0 hoac +84"
    )
    private String phoneNumber;

    @Pattern(
            regexp = "^$|^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
            message = "Kiem tra lai dinh dang email"
    )
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfIssue;

    private String placeOfIssue;

    private MultipartFile avatar;

    private MultipartFile idCardFront;

    private MultipartFile idCardBack;
}
