package com.example.springapi09.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
     String upload(MultipartFile file) throws IOException;
}
