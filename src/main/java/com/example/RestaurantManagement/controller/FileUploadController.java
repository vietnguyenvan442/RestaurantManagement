package com.example.RestaurantManagement.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/upload")
@CrossOrigin
public class FileUploadController {
    private static final String UPLOAD_DIR = "F:/Desktop/DATN/fronend_restaurant/public/images";

    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // Tạo đường dẫn thư mục nếu chưa tồn tại
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Lưu file vào thư mục
            String fileName = file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            file.transferTo(filePath.toFile());

            // Trả về đường dẫn ảnh (dùng cho React)
            return "/images/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to store file: " + e.getMessage());
        }
    }
}
