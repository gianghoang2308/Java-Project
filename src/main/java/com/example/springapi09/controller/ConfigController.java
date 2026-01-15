package com.example.springapi09.controller;

import com.example.springapi09.dto.Config.ConfigRequest;
import com.example.springapi09.dto.Config.ConfigResponse;
import com.example.springapi09.dto.Config.ConfigUpdate;
import com.example.springapi09.response.ResponseHandle;
import com.example.springapi09.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/config")
public class ConfigController {


    @Autowired
    private ConfigService configService;

    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        List<ConfigResponse> configResponses = configService.getAllConfigs();
        return ResponseHandle.responseBuilder("Successfully fetch configs from the database!", null, HttpStatus.OK, configResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getByID(@PathVariable Integer id) {
        ConfigResponse configResponse = configService.findById(id);
        return ResponseHandle.responseBuilder("Successfully fetch config detail from the database!", null, HttpStatus.OK, configResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody ConfigRequest configRequest) {
        String message = configService.create(configRequest);
        return ResponseHandle.responseBuilder("Successfully created the new config!", null, HttpStatus.OK, message);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody ConfigUpdate configUpdate) {
        String message = configService.update(configUpdate, id);
        return ResponseHandle.responseBuilder("Successfully updated the config!", null, HttpStatus.OK, message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        String message = configService.delete(id);
        return ResponseHandle.responseBuilder("Successfully deleted the config!", null, HttpStatus.OK, message);
    }
}
