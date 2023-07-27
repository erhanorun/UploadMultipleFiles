package com.project.UploadMultipleFiles.controllers;

import com.project.UploadMultipleFiles.exceptions.FileTypeExceptionResponse;
import com.project.UploadMultipleFiles.repositories.FileRepository;
import com.project.UploadMultipleFiles.responses.FileInfoResponse;
import com.project.UploadMultipleFiles.services.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class FileController {
    private final FileRepository fileRepository;
    private final FileService fileService;

    public FileController(FileRepository fileRepository, FileService fileService) {
        this.fileRepository = fileRepository;
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException, FileTypeExceptionResponse {
        fileService.saveFile(file);
        return ResponseEntity.status(HttpStatus.CREATED).body("File uploaded successfully.");
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileInfoResponse>> getListFiles() {
        fileRepository.findAll();

        List<FileInfoResponse> files = fileService.getAllFiles().map(fileDb -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(String.valueOf(fileDb.getId()))
                    .toUriString();

            return new FileInfoResponse(
                    fileDb.getName(),
                    fileDownloadUri,
                    fileDb.getContentType(),
                    fileDb.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

}
