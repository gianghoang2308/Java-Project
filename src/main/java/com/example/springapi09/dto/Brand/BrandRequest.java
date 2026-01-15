package com.example.springapi09.dto.Brand;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandRequest {

    @NotNull(message = "Ten thuong hieu khong duoc de trong")
    private String name;

    @NotNull(message = "Logo thuong hieu khong duoc de trong")
    private MultipartFile logo;

    @NotNull(message = "Mo ta thuong hieu khong duoc de trong")
    private String description;

}
