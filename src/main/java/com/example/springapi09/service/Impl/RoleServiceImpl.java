package com.example.springapi09.service.Impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.springapi09.dto.Role.RoleUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapi09.dto.Role.RoleRequest;
import com.example.springapi09.dto.Role.RoleResponse;
import com.example.springapi09.entity.Role;
import com.example.springapi09.exception.NotFoundException;
import com.example.springapi09.mapper.RoleMapper;
import com.example.springapi09.repository.RoleRepository;
import com.example.springapi09.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleResponse> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()) {
            throw new NotFoundException("Data cannot be null!");
        }
        return roleMapper.toResponseList(roles);
    }

    @Override
    public RoleResponse findById(Integer id){
        Optional<Role> role= roleRepository.findById(Objects.requireNonNull(id));
        if(role.isEmpty()){
            throw new NotFoundException("Data cannot be null!");
        }
        return roleMapper.toResponse(role.get());
    }

    @Override
    public String create(RoleRequest roleRequest) {
        if (roleRequest == null) {
            throw new NotFoundException("Data cannot be null!");
        }
        Role create = roleMapper.toRole(roleRequest);
        roleRepository.save(Objects.requireNonNull(create));
        return "Successfully create new role!";
    }

    @Override
    public String update(RoleUpdate roleUpdate, Integer id) {
        if (roleUpdate == null) {
            throw new NotFoundException("Data cannot be null!");
        }

        if (id == null) {
            throw new NotFoundException("Id cannot be null!");
        }

        Role existing = roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cannot find category with id: " + id));

        if (roleUpdate.getName() != null && !roleUpdate.getName().isEmpty()) {
            existing.setName(roleUpdate.getName());
        }


        roleRepository.save(existing);
        return "Successfully updated category with id: " + id;
    }

    @Override
    public String delete(Integer id) {
         Optional<Role> role= roleRepository.findById(Objects.requireNonNull(id));
        if(role.isEmpty()){
            throw new NotFoundException("Data cannot be null!");
        }
        roleRepository.deleteById(id);
        return "Successfully delete role with id: "+id;
    }
}
