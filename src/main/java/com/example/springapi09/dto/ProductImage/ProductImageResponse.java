package com.example.springapi09.dto.ProductImage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductImageResponse {

    private String images;

    private Integer productId;

    private String productName;
}
