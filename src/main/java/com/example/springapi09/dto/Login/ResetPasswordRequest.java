package com.example.springapi09.dto.Login;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequest {
    @NotBlank(message = "Username khong duoc de trong")
    private String username;

    @NotBlank(message = "Password moi khong duoc de trong")
    @Pattern(
            regexp = "^(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$",
            message = "Password phai co it nhat 8 ky tu va chua ky tu dac biet"
    )
    private String newPassword;
}
