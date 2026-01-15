package com.example.springapi09.dto.Product;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotNull(message = "Ten san pham khong de trong")
    private String name;

    @NotNull(message = "Anh san pham khong de trong")
    private MultipartFile mainImage;

    @NotNull(message = "Gia san pham khong de trong")
    private Double price;

    private Double salePrice;

    @NotNull(message = "Mo ta san pham khong duoc de trong")
    private String description;

    private Boolean status = true;

    private Integer categoryId;

    private Integer brandId;

    private Integer supplierId;

    private Integer assembleInId;

    private Integer colorId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
