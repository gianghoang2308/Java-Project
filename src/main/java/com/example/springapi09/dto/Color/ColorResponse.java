package com.example.springapi09.dto.Color;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColorResponse {

    private String colorCode;

    private String name;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
