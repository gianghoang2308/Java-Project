package com.example.springapi09.dto.Supplier;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierUpdate {
    private String name;

    private String address;

    @Pattern(
            regexp = "^$|^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
            message = "Kiem tra lai dinh dang email"
    )
    private String email;

    @Pattern(
            regexp = "^$|^(0\\d{9}|\\+84\\d{9})$",
            message = "So dien thoai phai co 10 chu so va bat dau bang 0 hoac +84"
    )
    private String phoneNumber;

    private String taxCode;

    private String website;

    private MultipartFile logo;
}
