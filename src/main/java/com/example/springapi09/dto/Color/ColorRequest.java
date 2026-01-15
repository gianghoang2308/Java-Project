package com.example.springapi09.dto.Color;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ColorRequest {

    @NotNull(message = "Ma mau khong duoc de trong")
    private String colorCode;

    @NotNull(message = "Ten mau khong duoc de trong")
    private String name;

}
