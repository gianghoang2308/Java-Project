package com.example.springapi09.service;

import java.util.List;

import com.example.springapi09.dto.AssembleIn.AssembleInRequest;
import com.example.springapi09.dto.AssembleIn.AssembleInResponse;
import com.example.springapi09.dto.AssembleIn.AssembleInUpdate;

public interface AssembleInService {
    List<AssembleInResponse> getAllAssembleIns();
    AssembleInResponse findById(Integer id);
    String create(AssembleInRequest assembleIn);
    String update(AssembleInUpdate assembleInUpdate, Integer id);
    String delete(Integer id);
}
