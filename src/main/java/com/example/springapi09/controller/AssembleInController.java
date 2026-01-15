package com.example.springapi09.controller;

import com.example.springapi09.dto.AssembleIn.AssembleInRequest;
import com.example.springapi09.dto.AssembleIn.AssembleInResponse;
import com.example.springapi09.dto.AssembleIn.AssembleInUpdate;
import com.example.springapi09.entity.AssembleIn;
import com.example.springapi09.response.ResponseHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.springapi09.service.AssembleInService;

import java.util.List;

@RestController
@RequestMapping("/assemblein")
public class AssembleInController {

    @Autowired
    private AssembleInService assembleInService;

    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        List<AssembleInResponse> assembleInResponses = assembleInService.getAllAssembleIns();
        return ResponseHandle.responseBuilder("Successfully fetch data from database", null, HttpStatus.OK, assembleInResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Integer id) {
        AssembleInResponse addAssembleInResponse = assembleInService.findById(id);
        return ResponseHandle.responseBuilder("Successfully request details!", null, HttpStatus.OK, addAssembleInResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody AssembleInRequest assembleInRequest) {
        String assembleInCreate = assembleInService.create(assembleInRequest);
        return ResponseHandle.responseBuilder("Successfully create new information!", null, HttpStatus.OK, assembleInCreate);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@RequestBody AssembleInUpdate assembleInUpdate, @PathVariable("id") Integer id) {
        String result = assembleInService.update(assembleInUpdate, id);
        return ResponseHandle.responseBuilder("Successfully update information with id: " + id, null, HttpStatus.OK, result);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
        String result = assembleInService.delete(id);
        return ResponseHandle.responseBuilder("Successfully delete information with id: " + id, null, HttpStatus.OK, result);
    }
}
