package com.example.springapi09.dto.User;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdate {

    private String userName;
    private String password;
    private String fullName;
    private MultipartFile avatar;


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
    private MultipartFile idCardFront;
    private MultipartFile idCardBack;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfIssue;
    private String placeOfIssue;
}
