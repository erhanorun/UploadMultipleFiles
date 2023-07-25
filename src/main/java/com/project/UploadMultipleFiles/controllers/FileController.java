package com.project.UploadMultipleFiles.controllers;

import com.project.UploadMultipleFiles.repositories.FileRepository;
import com.project.UploadMultipleFiles.services.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class FileController {
    FileRepository fileRepository;
    FileService fileService;

    public FileController(FileRepository fileRepository, FileService fileService) {
        this.fileRepository = fileRepository;
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        fileService.saveFile(file);
        return ResponseEntity.status(HttpStatus.CREATED).body("File uploaded successfully.");
    }

}
