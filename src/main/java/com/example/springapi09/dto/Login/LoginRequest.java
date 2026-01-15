package com.example.springapi09.dto.Login;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = "Username khong duoc de trong")
    private String name;

    @NotBlank(message = "Username khong duoc de trong")
    private String password;

}
