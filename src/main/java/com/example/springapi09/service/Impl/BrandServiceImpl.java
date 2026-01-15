package com.example.springapi09.service.Impl;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.springapi09.dto.Brand.BrandUpdate;
import com.example.springapi09.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapi09.dto.Brand.BrandRequest;
import com.example.springapi09.dto.Brand.BrandResponse;
import com.example.springapi09.entity.Brand;
import com.example.springapi09.exception.NotFoundException;
import com.example.springapi09.mapper.BrandMapper;
import com.example.springapi09.repository.BrandRepository;
import com.example.springapi09.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    BrandMapper brandMapper;

    @Autowired
    private FileUploadService fileUploadService;

    @Override
    public List<BrandResponse> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        if (brands.isEmpty()) {
            throw new NotFoundException("Data is null");
        }
        return brandMapper.toResponseList(brands);
    }

    @Override
    public BrandResponse findById(Integer id) {
        Optional<Brand> brand = brandRepository.findById(Objects.requireNonNull(id));
        if (brand.isEmpty()) {
            throw new NotFoundException("Data cannot be null!");
        }
        return brandMapper.toBrandResponse(brand.get());
    }

    @Override
    public String create(BrandRequest brandRequest) {
        if (brandRequest == null) {
            throw new NotFoundException("Data cannot be null!");
        }

        String logoFilePath = null;

        try {
            logoFilePath = fileUploadService.upload(brandRequest.getLogo());
        } catch (IOException e) {
            throw new RuntimeException("Upload file failed: " + e.getMessage());
        }

        Brand create = brandMapper.toBrand(brandRequest);
        create.setLogo(logoFilePath);

        brandRepository.save(Objects.requireNonNull(create));

        return "Successfully created new brand with name: " + create.getName();
    }


    @Override
    public String update(BrandUpdate brandUpdate, Integer id) {
        if (brandUpdate == null) {
            throw new NotFoundException("Data cannot be null!");
        }

        if (id == null) {
            throw new NotFoundException("Id cannot be null!");
        }

        Brand existing = brandRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cannot find brand with id: " + id));

        try {
            if (brandUpdate.getName() != null && !brandUpdate.getName().isEmpty()) {
                existing.setName(brandUpdate.getName());
            } else if (existing.getName() != null) {
                existing.setName(existing.getName());
            }

            if (brandUpdate.getLogo() != null && !brandUpdate.getLogo().isEmpty()) {
                existing.setLogo(fileUploadService.upload(brandUpdate.getLogo()));
            } else if (existing.getLogo() != null) {
                existing.setLogo(existing.getLogo());
            }

            if (brandUpdate.getDescription() != null && !brandUpdate.getDescription().isEmpty()) {
                existing.setDescription(brandUpdate.getDescription());
            } else if (existing.getDescription() != null) {
                existing.setDescription(existing.getDescription());
            }

        } catch (Exception e) {
            throw new RuntimeException("Upload file failed: " + e.getMessage());
        }

        brandRepository.save(Objects.requireNonNull(existing));

        return "Successfully updated brand with id: " + id;
    }


    @Override
    public String delete(Integer id) {
        Optional<Brand> brand = brandRepository.findById(Objects.requireNonNull(id));
        if (brand.isEmpty()) {
            throw new NotFoundException("Data cannot be null!");
        }
        brandRepository.deleteById(id);
        return "Successfully delete brand with id: " + id;
    }
}
