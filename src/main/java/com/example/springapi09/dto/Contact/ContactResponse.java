package com.example.springapi09.dto.Contact;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ContactResponse {

    private String fullName;

    private String phoneNumber;

    private String address;

    private String email;

    private String message;
}
