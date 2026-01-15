package com.example.springapi09.dto.ProductImage;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageRequest {

    @NotNull(message = "Ten anh san pham khong duoc de trong")
    private List<MultipartFile> images;
    
    @NotNull(message = "ProductId khong duoc de trong")
    private Integer productId;
}
