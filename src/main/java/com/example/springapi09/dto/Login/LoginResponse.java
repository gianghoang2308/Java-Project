package com.example.springapi09.dto.Login;

import com.example.springapi09.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private Integer id;
    private String name;
    private String fullName;
    private Role role;
    private String token;
}
