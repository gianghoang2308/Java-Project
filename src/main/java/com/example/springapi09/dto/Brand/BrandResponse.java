package com.example.springapi09.dto.Brand;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BrandResponse {

    private String name;

    private String logo;

    private String description;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
