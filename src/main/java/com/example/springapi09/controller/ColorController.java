package com.example.springapi09.controller;

import com.example.springapi09.dto.Color.ColorRequest;
import com.example.springapi09.dto.Color.ColorResponse;
import com.example.springapi09.dto.Color.ColorUpdate;
import com.example.springapi09.response.ResponseHandle;
import com.example.springapi09.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/color")
public class ColorController {
    @Autowired
    private ColorService colorService;

    @GetMapping("/")
    public ResponseEntity<Object> getAllColors() {
        List<ColorResponse> colorResponses = colorService.getAllColors();
        return ResponseHandle.responseBuilder("Successfully fetch color from the database", null, HttpStatus.OK, colorResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getColorById(@PathVariable Integer id) {
        ColorResponse colorResponse = colorService.findById(id);
        return ResponseHandle.responseBuilder("Get category detail", null, HttpStatus.OK, colorResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createColor(@RequestBody ColorRequest colorRequest) {
        String message = colorService.create(colorRequest);
        return ResponseHandle.responseBuilder("Successfully create new color", null, HttpStatus.OK, message);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateColor(@PathVariable Integer id, @RequestBody ColorUpdate colorUpdate) {
        String message = colorService.update(colorUpdate, id);
        return ResponseHandle.responseBuilder("Successfully update color", null, HttpStatus.OK, message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteColor(@PathVariable Integer id) {
        String message = colorService.delete(id);
        return ResponseHandle.responseBuilder("Successfully delete color", null, HttpStatus.OK, message);
    }

}
