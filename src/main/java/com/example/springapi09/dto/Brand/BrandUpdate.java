package com.example.springapi09.dto.Brand;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandUpdate {
    private String name;

    private MultipartFile logo;

    private String description;
}
