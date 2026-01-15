package com.example.springapi09.service.Impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.springapi09.dto.AssembleIn.AssembleInUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapi09.dto.AssembleIn.AssembleInRequest;
import com.example.springapi09.dto.AssembleIn.AssembleInResponse;
import com.example.springapi09.entity.AssembleIn;
import com.example.springapi09.exception.NotFoundException;
import com.example.springapi09.mapper.AssembleInMapper;
import com.example.springapi09.repository.AssembleInRepository;
import com.example.springapi09.service.AssembleInService;

@Service
public class AssembleInServiceImpl implements AssembleInService {

    @Autowired
    private AssembleInRepository assembleInRepository;

    @Autowired
    private AssembleInMapper assembleInMapper;

    @Override
    public List<AssembleInResponse> getAllAssembleIns() {
        List<AssembleIn> assembleIns = assembleInRepository.findAll();
        if (assembleIns.isEmpty()) {
            throw new NotFoundException("Data in the databse is null");
        }
        return assembleInMapper.toResponseList(assembleIns);
    }

    @Override
    public AssembleInResponse findById(Integer id) {

        Optional<AssembleIn> assembleIn = assembleInRepository.findById(Objects.requireNonNull(id));
        if (assembleIn.isEmpty()) {
            throw new NotFoundException("Cannot find assembleIn with id: " + id);
        }
        return assembleInMapper.toAssembleInResponse(assembleIn.get());
    }

    @Override
    public String create(AssembleInRequest assembleInRequest) {
        if (assembleInRequest == null) {
            throw new IllegalArgumentException("Request body cannot be null");
        }

        AssembleIn create = assembleInMapper.toAssembleIn(assembleInRequest);
        assembleInRepository.save(Objects.requireNonNull(create));

        return "Successfully created new assembleIn " + create;
    }

    @Override
    public String update(AssembleInUpdate assembleInUpdate, Integer id) {
        if (assembleInUpdate == null) {
            throw new NotFoundException("Data cannot be null");
        }

        if (id == null) {
            throw new NotFoundException("Id cannot be null");
        }


        AssembleIn existing = assembleInRepository.findById(Objects.requireNonNull(id))
                .orElseThrow(() -> new NotFoundException("Cannot update assembleIn with id: " + id));



        assembleInMapper.updateEntity(assembleInUpdate, existing);

        try{
            if(assembleInUpdate.getCountry() != null && !assembleInUpdate.getCountry().isEmpty()) {
                existing.setCountry(assembleInUpdate.getCountry());
            }
        }
        catch (Exception e){
            throw new RuntimeException("Upload file failed: " + e.getMessage());
        }
        assembleInRepository.save(Objects.requireNonNull(existing));
        return "Successfully update assembleIn with id: " + id;
    }

    @Override
    public String delete(Integer id) {
        Optional<AssembleIn> assembleIn = assembleInRepository.findById(Objects.requireNonNull(id));
        if (assembleIn.isEmpty()) {
            throw new NotFoundException("Cannot find admin with id: " + id);
        }
        assembleInRepository.deleteById(id);
        return "Successfully delete assembleIn with id: " + id;
    }
}
