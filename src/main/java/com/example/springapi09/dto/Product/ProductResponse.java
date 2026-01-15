package com.example.springapi09.dto.Product;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {

    private String name;

    private String mainImage;

    private Double price;

    private Double salePrice;

    private String description;

    private Boolean status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer categoryId;
    private String categoryName;

    private Integer brandId;
    private String brandName;

    private Integer supplierId;
    private String supplierName;

    private Integer assembleInId;
    private String assembleInCountry;

    private Integer colorId;
    private String colorName;
}
