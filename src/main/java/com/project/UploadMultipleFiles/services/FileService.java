package com.project.UploadMultipleFiles.services;

import com.project.UploadMultipleFiles.entities.FileEntity;
import com.project.UploadMultipleFiles.repositories.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class FileService implements FileServiceImplementation {
    FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public FileEntity saveFile(MultipartFile file) throws IOException {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        FileEntity fileEntity = new FileEntity(fileName, file.getContentType(), file.getBytes());

        return fileRepository.save(fileEntity);

    }

}
