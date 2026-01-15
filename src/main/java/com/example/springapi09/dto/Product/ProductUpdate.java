package com.example.springapi09.dto.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdate {
    private String name;

    private MultipartFile mainImage;

    private Double price;

    private Double salePrice;

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
