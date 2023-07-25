package com.project.UploadMultipleFiles.responses;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class FileInfoResponse {

    private String name;
    private String type;
    private String url;
    private long size;
}
