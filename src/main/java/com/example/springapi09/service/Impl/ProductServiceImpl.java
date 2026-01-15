package com.example.springapi09.service.Impl;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


import com.example.springapi09.dto.Product.ProductUpdate;
import com.example.springapi09.entity.*;
import com.example.springapi09.repository.*;
import com.example.springapi09.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapi09.dto.Product.ProductRequest;
import com.example.springapi09.dto.Product.ProductResponse;
import com.example.springapi09.exception.NotFoundException;
import com.example.springapi09.mapper.ProductMapper;
import com.example.springapi09.service.ProductService;
import org.springframework.transaction.annotation.Transactional;

;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    AssembleInRepository assembleInRepository;

    @Autowired
    ColorRepository colorRepository;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    FileUploadService fileUploadService;

    @Transactional
    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new NotFoundException("Data cannot be null!");
        }
        return productMapper.toResponseList(products);
    }

    @Transactional
    @Override
    public ProductResponse findById(Integer id) {
        Optional<Product> product = productRepository.findById(Objects.requireNonNull(id));
        if (product.isEmpty()) {
            throw new NotFoundException("Data cannot be null!");
        }
        return productMapper.toResponse(product.get());
    }

    @Override
    public String create(ProductRequest productRequest) {
        if (productRequest == null) {
            throw new NotFoundException("Data cannot be null!");
        }

        if (productRequest.getCategoryId() == null) {
            throw new IllegalArgumentException("Category ID cannot be null!");
        }
        if (productRequest.getBrandId() == null) {
            throw new IllegalArgumentException("Brand ID cannot be null!");
        }
        if (productRequest.getSupplierId() == null) {
            throw new IllegalArgumentException("Supplier ID cannot be null!");
        }

        if (productRequest.getAssembleInId() == null) {
            throw new IllegalArgumentException("AssembleIn ID cannot be null!");
        }

        if (productRequest.getColorId() == null) {
            throw new IllegalArgumentException("Color ID cannot be null!");
        }

        if (productRequest.getSalePrice() == null) {
            productRequest.setSalePrice(productRequest.getPrice() * 0.9);
        }

        String logoFilePath = null;

        try {
            logoFilePath = fileUploadService.upload(productRequest.getMainImage());
        } catch (IOException e) {
            throw new RuntimeException("Upload file failed: " + e.getMessage());
        }

        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found"));

        Brand brand = brandRepository.findById(productRequest.getBrandId())
                .orElseThrow(() -> new NotFoundException("Brand not found"));

        Supplier supplier = supplierRepository.findById(productRequest.getSupplierId())
                .orElseThrow(() -> new NotFoundException("Supplier not found"));

        AssembleIn assembleIn = assembleInRepository.findById(productRequest.getAssembleInId())
                .orElseThrow(() -> new NotFoundException("AssembleIn not found"));

        Color color = colorRepository.findById(productRequest.getColorId())
                .orElseThrow(() -> new NotFoundException("Color not found"));

        Product create = productMapper.toProduct(productRequest);
        create.setMainImage(logoFilePath);
        create.setCategory(category);
        create.setBrand(brand);
        create.setSupplier(supplier);
        create.setAssembleIn(assembleIn);
        create.setColor(color);

        productRepository.save(Objects.requireNonNull(create));

        return "Successfully created new product with name: " + create.getName();
    }

    @Override
    public String update(ProductUpdate productUpdate, Integer id) {
        if (productUpdate == null) {
            throw new NotFoundException("Data cannot be null!");
        }

        if (id == null) {
            throw new NotFoundException("Id cannot be null!");
        }

        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cannot find brand with id: " + id));

        try {
            if (productUpdate.getName() != null && !productUpdate.getName().isEmpty()) {
                existing.setName(productUpdate.getName());
            }

            if (productUpdate.getMainImage() != null && !productUpdate.getMainImage().isEmpty()) {
                existing.setMainImage(fileUploadService.upload(productUpdate.getMainImage()));
            }

            if (productUpdate.getPrice() != null) {
                existing.setPrice(productUpdate.getPrice());
                existing.setSalePrice(productUpdate.getPrice() * 0.9);
            }

            if (productUpdate.getSalePrice() != null) {
                existing.setSalePrice(productUpdate.getSalePrice());
            }

            if (productUpdate.getDescription() != null && !productUpdate.getDescription().isEmpty()) {
                existing.setDescription(productUpdate.getDescription());
            }

            if (productUpdate.getStatus() != null) {
                existing.setStatus(productUpdate.getStatus());
            }

            if (productUpdate.getCategoryId() != null) {
                Category category = categoryRepository.findById(productUpdate.getCategoryId())
                        .orElseThrow(() -> new NotFoundException("Category not found"));
                existing.setCategory(category);
            }

            if (productUpdate.getBrandId() != null) {
                Brand brand = brandRepository.findById(productUpdate.getBrandId())
                        .orElseThrow(() -> new NotFoundException("Brand not found"));
                existing.setBrand(brand);
            }

            if (productUpdate.getSupplierId() != null) {
                Supplier supplier = supplierRepository.findById(productUpdate.getSupplierId())
                        .orElseThrow(() -> new NotFoundException("Supplier not found"));
                existing.setSupplier(supplier);
            }

            if (productUpdate.getAssembleInId() != null) {
                AssembleIn assembleIn = assembleInRepository.findById(productUpdate.getAssembleInId())
                        .orElseThrow(() -> new NotFoundException("AssembleIn not found"));
                existing.setAssembleIn(assembleIn);
            }

            if (productUpdate.getColorId() != null) {
                Color color = colorRepository.findById(productUpdate.getColorId())
                        .orElseThrow(() -> new NotFoundException("Color not found"));
                existing.setColor(color);
            }

        } catch (Exception e) {
            throw new RuntimeException("Upload file failed: " + e.getMessage());
        }

        productRepository.save(Objects.requireNonNull(existing));

        return "Successfully updated brand with id: " + id;
    }

    @Override
    public String delete(Integer id) {
        Optional<Product> product = productRepository.findById(Objects.requireNonNull(id));
        if (product.isEmpty()) {
            throw new NotFoundException("Data cannot be null!");
        }
        productRepository.deleteById(id);
        return "Successfully delete product with id: " + id;
    }
}
