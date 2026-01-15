package com.example.springapi09.dto.Category;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse {

    private String name;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
