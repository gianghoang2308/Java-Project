package com.example.springapi09.service.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.springapi09.dto.ProductImage.ProductImageUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springapi09.dto.ProductImage.ProductImageRequest;
import com.example.springapi09.dto.ProductImage.ProductImageResponse;
import com.example.springapi09.entity.Product;
import com.example.springapi09.entity.ProductImage;
import com.example.springapi09.exception.NotFoundException;
import com.example.springapi09.mapper.ProductImageMapper;
import com.example.springapi09.repository.ProductImageRepository;
import com.example.springapi09.repository.ProductRepository;
import com.example.springapi09.service.FileUploadService;
import com.example.springapi09.service.ProductImageService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    ProductImageRepository productImageRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductImageMapper productImageMapper;

    @Autowired
    FileUploadService fileUploadService;

    @Transactional
    @Override
    public List<ProductImageResponse> getAllProductImages() {
        List<ProductImage> productImages = productImageRepository.findAll();
        if (productImages.isEmpty()) {
            throw new NotFoundException("Data cannot be null");
        }
        return productImageMapper.toResponseList(productImages);
    }

    @Transactional
    @Override
    public ProductImageResponse findById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid product image ID.");
        }

        Optional<ProductImage> productImage = productImageRepository.findById(id);
        if (productImage.isEmpty()) {
            throw new NotFoundException("Product image with ID " + id + " not found.");
        }

        return productImageMapper.toResponse(productImage.get());
    }


    @Transactional
    @Override
    public String create(ProductImageRequest productImageRequest) {

        if (productImageRequest == null) {
            throw new NotFoundException("Data cannot be null!");
        }

        if (productImageRequest.getProductId() == null) {
            throw new IllegalArgumentException("Product ID cannot be null!");
        }

        Product product = productRepository.findById(productImageRequest.getProductId())
                .orElseThrow(() -> new NotFoundException("Product not found for the given Product ID"));

        if (productImageRequest.getImages() == null || productImageRequest.getImages().isEmpty()) {
            throw new IllegalArgumentException("At least one image must be provided!");
        }

        List<String> fileNames = new ArrayList<>();

        for (MultipartFile file : productImageRequest.getImages()) {
            try {
                String fileName = fileUploadService.upload(file); // Upload the file and get the file name
                fileNames.add(fileName);  // Add the file name to the list
            } catch (IOException e) {
                throw new RuntimeException("Upload failed for file: " + file.getOriginalFilename(), e);
            }
        }

        // Convert the list of file names into a comma-separated string
        String fileNamesString = String.join(",", fileNames);

        // Map the request to a ProductImage entity
        ProductImage create = productImageMapper.toProduct(productImageRequest);
        create.setImages(fileNamesString); // Set the comma-separated image filenames
        create.setProduct(product); // Set the associated product

        // Save the entity to the database
        productImageRepository.save(create);

        // Return a success message
        return "Successfully uploaded product images!";
    }

    @Override
    public String update(ProductImageUpdate productUpdate, Integer productImageId) {
        // Kiểm tra nếu dữ liệu không hợp lệ
        if (productUpdate == null) {
            throw new NotFoundException("Data cannot be null!");
        }

        if (productImageId == null) {
            throw new NotFoundException("Product Image ID cannot be null!");
        }

        // Tìm hình ảnh sản phẩm theo ID
        ProductImage existingProductImage = productImageRepository.findById(productImageId)
                .orElseThrow(() -> new NotFoundException("Cannot find product image with id: " + productImageId));

        try {
            // Cập nhật productId nếu có
            if (productUpdate.getProductId() != null) {
                Product product = productRepository.findById(productUpdate.getProductId())
                        .orElseThrow(() -> new NotFoundException("Product not found"));
                existingProductImage.setProduct(product);
            }

            // Kiểm tra xem có ảnh mới không
            if (productUpdate.getImages() != null && !productUpdate.getImages().isEmpty()) {
                // Nếu có ảnh mới, upload và cập nhật danh sách ảnh
                List<String> fileNames = new ArrayList<>();
                for (MultipartFile file : productUpdate.getImages()) {
                    if (file.isEmpty()) {
                        // Nếu tệp trống, bỏ qua tệp đó và không ném lỗi
                        continue; // Bỏ qua ảnh trống
                    }

                    try {
                        String fileName = fileUploadService.upload(file); // Upload tệp
                        fileNames.add(fileName); // Thêm tên tệp vào danh sách
                    } catch (IOException e) {
                        throw new RuntimeException("File upload failed for image: " + e.getMessage());
                    }
                }


                if (!fileNames.isEmpty()) {
                    String fileNamesString = String.join(",", fileNames); // Nối tên các tệp thành chuỗi
                    existingProductImage.setImages(fileNamesString); // Cập nhật ảnh mới vào entity
                }
            } else {
                if (existingProductImage.getImages() != null && !existingProductImage.getImages().isEmpty()) {
                    existingProductImage.setImages(existingProductImage.getImages());
                }
            }


        } catch (Exception e) {
            throw new RuntimeException("Error updating product image: " + e.getMessage());
        }
        productImageRepository.save(existingProductImage);



        return "Successfully updated product image with id: " + productImageId;
    }




    @Override
    public String delete(Integer id) {
        Optional<ProductImage> productImage = productImageRepository.findById(Objects.requireNonNull(id));
        if (productImage.isEmpty()) {
            throw new NotFoundException("Data cannot be null");
        }
        productImageRepository.deleteById(id);
        return "Successfully delete product's images with id: " + id;
    }
}
