package com.example.springapi09.dto.Config;

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
public class ConfigRequest {

    @NotNull(message = "Email khong duoc de trong")
    @Email(message = "Kiem tra lai dinh dang email")
    private String email1;

    @NotNull(message = "Email khong duoc de trong")
    @Email(message = "Kiem tra lai dinh dang email")
    private String email2;

    @NotNull(message = "Email khong duoc de trong")
    @Email(message = "Kiem tra lai dinh dang email")
    private String email3;

    @NotNull(message = "Email khong duoc de trong")
    @Email(message = "Kiem tra lai dinh dang email")
    private String email4;

    @NotNull(message = "Social link khong duoc de trong")
    private String socialLink1;

    @NotNull(message = "Social link khong duoc de trong")
    private String socialLink2;

    @NotNull(message = "Social link khong duoc de trong")
    private String socialLink3;

    @NotNull(message = "Social link khong duoc de trong")
    private String socialLink4;

    @NotNull(message = "Thong tin lien he khong duoc de trong")
    private String contact;

    @NotNull(message = "Tieu de khong duoc de trong")
    private String title;

    @NotNull(message = "Mo ta khong duoc de trong")
    private String description;

    @NotNull(message = "So hotline lien lac khong duoc de trong")
    private String hotLine;

    @NotNull(message = "Dia chi khong duoc de trong")
    private String address;
}
