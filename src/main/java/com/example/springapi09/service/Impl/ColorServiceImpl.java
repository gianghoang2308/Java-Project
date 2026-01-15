package com.example.springapi09.service.Impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.springapi09.dto.Color.ColorUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapi09.dto.Color.ColorRequest;
import com.example.springapi09.dto.Color.ColorResponse;
import com.example.springapi09.entity.Color;
import com.example.springapi09.exception.NotFoundException;
import com.example.springapi09.mapper.ColorMapper;
import com.example.springapi09.repository.ColorRepository;
import com.example.springapi09.service.ColorService;

@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    ColorRepository colorRepository;

    @Autowired
    ColorMapper colorMapper;

    @Override
    public List<ColorResponse> getAllColors() {
        List<Color> colors = colorRepository.findAll();
        if (colors.isEmpty()) {
            throw new NotFoundException("Data is not null!");
        }

        return colorMapper.toResponseList(colors);
    }

    @Override
    public ColorResponse findById(Integer id) {
        Optional<Color> color = colorRepository.findById(Objects.requireNonNull(id));
        if (color.isEmpty()) {
            throw new NotFoundException("Data cannot be null!");
        }
        return colorMapper.toColorResponse(color.get());
    }

    @Override
    public String create(ColorRequest colorRequest) {
        if (colorRequest == null) {
            throw new NotFoundException("Data cannot be null!");
        }
        Color create = colorMapper.toColor(colorRequest);
        colorRepository.save(Objects.requireNonNull(create));
        return "Successfully create new color!";
    }

    @Override
    public String update(ColorUpdate colorUpdate, Integer id) {
        if (colorUpdate == null) {
            throw new NotFoundException("Data cannot be null!");
        }

        if (id == null) {
            throw new NotFoundException("Id cannot be null!");
        }

        Color existing = colorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cannot find category with id: " + id));

        if (colorUpdate.getName() != null && !colorUpdate.getName().isEmpty()) {
            existing.setName(colorUpdate.getName());
        }

        if(colorUpdate.getColorCode() != null && !colorUpdate.getColorCode().isEmpty()) {
            existing.setColorCode(colorUpdate.getColorCode());
        }
        colorRepository.save(existing);
        return "Successfully updated category with id: " + id;
    }

    @Override
    public String delete(Integer id) {
        Optional<Color> color = colorRepository.findById(Objects.requireNonNull(id));
        if (color.isEmpty()) {
            throw new NotFoundException("Data cannot be null!");
        }
        colorRepository.deleteById(id);
        return "Successfully delete color with id: " + id;
    }
}
