package com.project.UploadMultipleFiles.services;

import com.project.UploadMultipleFiles.entities.FileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileServiceImplementation {
    FileEntity saveFile(MultipartFile file) throws IOException;
}
