package com.example.springapi09.dto.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest {

    @NotNull(message = "Khong duoc de trong phan nay")
    @NotBlank(message = "Khong duoc de trong phan nay")
    private String name;
}
