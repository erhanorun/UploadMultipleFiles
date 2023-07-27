package com.project.UploadMultipleFiles.services;

import com.project.UploadMultipleFiles.entities.FileEntity;
import com.project.UploadMultipleFiles.exceptions.FileTypeExceptionResponse;
import com.project.UploadMultipleFiles.repositories.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class FileService implements FileServiceImplementation {
    private final FileRepository fileRepository;

//    public FileService(FileRepository fileRepository) {
//        this.fileRepository = fileRepository;
//    }

    @Value(value = "${data.exception.message}")
    private String invalidMessage;


    private boolean matchFileExtension(String extension) {
        return extension != null && (
                extension.equals("png") || extension.equals("jpeg")
                        || extension.equals("jpg") || extension.equals("docx")
                        || extension.equals("pdf") || extension.equals("xlsx"));
    }

    public FileEntity saveFile(MultipartFile file) throws IOException, FileTypeExceptionResponse {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        FileEntity fileEntity = new FileEntity(fileName, file.getContentType(), file.getBytes());

        String extension = FilenameUtils.getExtension(
                file.getOriginalFilename());
        if (!matchFileExtension(extension))
            throw new FileTypeExceptionResponse(new Date(), invalidMessage);
        return fileRepository.save(fileEntity);

    }

    public Stream<FileEntity> getAllFiles() {
        return fileRepository.findAll().stream();
    }

}
