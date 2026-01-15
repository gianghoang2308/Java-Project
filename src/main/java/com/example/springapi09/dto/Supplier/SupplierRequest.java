package com.example.springapi09.dto.Supplier;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierRequest {

    @NotNull(message = "Ten nha cung cap khong duoc de trong")
    private String name;

    @NotNull(message = "Dia chi nha cung cap khong duoc de trong")
    private String address;

    @NotNull(message = "Email nha cung cap khong duoc de trong")
    @Email(message = "Kiem tra lai dinh dang email")

    private String email;

    @NotNull(message = "So dien thoai nha cung cao khong duoc de trong")
    @Pattern(
            regexp = "^(0\\d{9}|\\+84\\d{9})$",
            message = "So dien thoai phai co 10 chu so va bat dau bang 0 hoac +84"
    )
    private String phoneNumber;

    @NotNull(message = "Ma so thue nha cung cap khong duoc de trong")
    private String taxCode;

    @NotNull(message = "Dia chi website cua nha cung cap khong duoc de trong")
    private String website;

    @NotNull(message = "Logo cua nha cung cap khong duoc de trong")
    private MultipartFile logo;

}
