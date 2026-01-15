package com.example.springapi09.service;

import java.util.List;

import com.example.springapi09.dto.Color.ColorRequest;
import com.example.springapi09.dto.Color.ColorResponse;
import com.example.springapi09.dto.Color.ColorUpdate;

public interface ColorService {
    List<ColorResponse> getAllColors();
    ColorResponse findById(Integer id);
    String create(ColorRequest colorRequest);
    String update(ColorUpdate colorUpdate, Integer id);
    String delete(Integer id);
}
