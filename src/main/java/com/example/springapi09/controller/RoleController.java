package com.example.springapi09.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springapi09.dto.Role.RoleRequest;
import com.example.springapi09.dto.Role.RoleResponse;
import com.example.springapi09.response.ResponseHandle;
import com.example.springapi09.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/")
    public ResponseEntity<Object> getAll() {
        List<RoleResponse> roles = roleService.getAllRoles();
        return ResponseHandle.responseBuilder("Successfully fetch data", null, HttpStatus.OK, roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Integer id) {
        RoleResponse role = roleService.findById(id);
        return ResponseHandle.responseBuilder("Successfully get detail information!", null, HttpStatus.OK, role);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody RoleRequest role) {
        String result = roleService.create(role);
        return ResponseHandle.responseBuilder("Successfully create new role!", null, HttpStatus.CREATED, result);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@RequestBody RoleRequest role, @PathVariable("id") Integer id) {
        String result = roleService.create(role);
        return ResponseHandle.responseBuilder("Successfully update  role!", null, HttpStatus.OK, result);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id){
        String result = roleService.delete(id);
        return ResponseHandle.responseBuilder("name", null,HttpStatus.OK, result);
    }
}
