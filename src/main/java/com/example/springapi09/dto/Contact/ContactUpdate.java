package com.example.springapi09.dto.Contact;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactUpdate {

    private String fullName;
    private String phoneNumber;
    private String address;

    @Email(message = "Kiem tra lai dinh dang email")
    private String email;
    private String message;

}
