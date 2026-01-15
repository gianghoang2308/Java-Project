package com.example.springapi09.dto.Contact;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequest {

    @NotNull(message = "Ho va ten khong duoc de trong")
    private String fullName;

    @NotNull(message = "So dien thoai khong duoc de trong")
    private String phoneNumber;

    @NotNull(message = "Dia chi khong duoc de trong")
    private String address;

    @NotNull(message = "Email khong duoc de trong")
    @Email(message = "Kiem tra lai dinh dang email")
    private String email;

    @NotNull(message = "Message khong duoc de trong")
    private String message;
}
