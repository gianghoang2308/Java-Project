package com.example.springapi09.dto.Color;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColorUpdate {

    private String colorCode;
    private String name;
}
