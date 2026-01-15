package com.example.springapi09.service.Impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.springapi09.dto.Config.ConfigUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapi09.dto.Config.ConfigRequest;
import com.example.springapi09.dto.Config.ConfigResponse;
import com.example.springapi09.entity.Config;
import com.example.springapi09.exception.NotFoundException;
import com.example.springapi09.mapper.ConfigMapper;
import com.example.springapi09.repository.ConfigRepository;
import com.example.springapi09.service.ConfigService;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigRepository configRepository;

    @Autowired
    private ConfigMapper configMapper;

    @Override
    public List<ConfigResponse> getAllConfigs() {
        List<Config> configs = configRepository.findAll();
        if (configs.isEmpty()) {
            throw new NotFoundException("Data cannot be null!");
        }
        return configMapper.toResponseList(configs);
    }

    @Override
    public ConfigResponse findById(Integer id) {
        Optional<Config> config = configRepository.findById(Objects.requireNonNull(id));
        if (config.isEmpty()) {
            throw new NotFoundException("Data cannot be null!");
        }
        return configMapper.toResponse(config.get());
    }

    @Override
    public String create(ConfigRequest configRequest) {
        if (configRequest == null) {
            throw new NotFoundException("Data cannot be null!");
        }

        Config create = configMapper.toConfig(configRequest);
        configRepository.save(Objects.requireNonNull(create));
        return "Successfully create new config";
    }

    @Override
    public String update(ConfigUpdate configUpdate, Integer id) {
        if (configUpdate == null) {
            throw new NotFoundException("Data cannot be null!");
        }

        if (id == null) {
            throw new NotFoundException("Id cannot be null!");
        }

        Config existing = configRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cannot find category with id: " + id));

        if (configUpdate.getEmail1() != null && !configUpdate.getEmail1().isEmpty()) {
            existing.setEmail1(configUpdate.getEmail1());
        }

        if (configUpdate.getEmail2() != null && !configUpdate.getEmail2().isEmpty()) {
            existing.setEmail2(configUpdate.getEmail2());
        }

        if (configUpdate.getEmail3() != null && !configUpdate.getEmail3().isEmpty()) {
            existing.setEmail3(configUpdate.getEmail3());
        }

        if (configUpdate.getEmail4() != null && !configUpdate.getEmail4().isEmpty()) {
            existing.setEmail4(configUpdate.getEmail4());
        }

        if (configUpdate.getSocialLink1() != null && !configUpdate.getSocialLink1().isEmpty()) {
            existing.setSocialLink1(configUpdate.getSocialLink1());
        }

        if (configUpdate.getSocialLink2() != null && !configUpdate.getSocialLink2().isEmpty()) {
            existing.setSocialLink2(configUpdate.getSocialLink2());
        }

        if (configUpdate.getSocialLink3() != null && !configUpdate.getSocialLink3().isEmpty()) {
            existing.setSocialLink3(configUpdate.getSocialLink3());
        }

        if (configUpdate.getSocialLink4() != null && !configUpdate.getSocialLink4().isEmpty()) {
            existing.setSocialLink4(configUpdate.getSocialLink4());
        }

        if (configUpdate.getContact() != null && !configUpdate.getContact().isEmpty()) {
            existing.setContact(configUpdate.getContact());
        }

        if (configUpdate.getTitle() != null && !configUpdate.getTitle().isEmpty()) {
            existing.setTitle(configUpdate.getTitle());
        }

        if (configUpdate.getDescription() != null && !configUpdate.getDescription().isEmpty()) {
            existing.setDescription(configUpdate.getDescription());
        }

        if (configUpdate.getHotLine() != null && !configUpdate.getHotLine().isEmpty()) {
            existing.setHotLine(configUpdate.getHotLine());
        }

        if (configUpdate.getAddress() != null && !configUpdate.getAddress().isEmpty()) {
            existing.setAddress(configUpdate.getAddress());
        }
        configRepository.save(existing);
        return "Successfully updated category with id: " + id;
    }


    @Override
    public String delete(Integer id) {
        Optional<Config> config = configRepository.findById(Objects.requireNonNull(id));
        if (config.isEmpty()) {
            throw new NotFoundException("Data cannot be null!");
        }
        configRepository.deleteById(id);
        return "Successfully delete config with id: " + id;
    }

}
