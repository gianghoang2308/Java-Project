package com.example.springapi09.dto.Config;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConfigUpdate {
    @Email(message = "Kiem tra lai dinh dang email")
    private String email1;

    @Email(message = "Kiem tra lai dinh dang email")
    private String email2;

    @Email(message = "Kiem tra lai dinh dang email")
    private String email3;

    @Email(message = "Kiem tra lai dinh dang email")
    private String email4;

    private String socialLink1;
    private String socialLink2;
    private String socialLink3;
    private String socialLink4;
    private String contact;
    private String title;
    private String description;
    private String hotLine;
    private String address;
}
