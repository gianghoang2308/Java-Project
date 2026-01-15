package com.example.springapi09.dto.User;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterUpdate {


    @Size(min = 6, message = "Mat khau phai co it nhat 6 ky tu")
    private String password;

    @Size(max = 250, message = "Ho ten toi da 250 ky tu")
    private String fullName;

    private MultipartFile avatar;

    @Pattern(
            regexp = "^(|0[3|5|7|8|9][0-9]{8})$",
            message = "So dien thoai khong dung dinh dang Viet Nam"
    )
    private String phoneNumber;

    @Pattern(
            regexp = "^(|[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+)$",
            message = "Email khong dung dinh dang"
    )
    private String email;

    private MultipartFile  idCardFront;

    private MultipartFile  idCardBack;

    private LocalDate dateOfIssue;

    private String placeOfIssue;

}