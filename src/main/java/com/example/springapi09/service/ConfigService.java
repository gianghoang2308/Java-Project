package com.example.springapi09.service;

import java.util.List;

import com.example.springapi09.dto.Config.ConfigRequest;
import com.example.springapi09.dto.Config.ConfigResponse;
import com.example.springapi09.dto.Config.ConfigUpdate;

public interface ConfigService {
    List<ConfigResponse> getAllConfigs();
    ConfigResponse findById(Integer id);
    String create(ConfigRequest configRequest);
    String update(ConfigUpdate configUpdate, Integer id);
    String delete(Integer id);
}
