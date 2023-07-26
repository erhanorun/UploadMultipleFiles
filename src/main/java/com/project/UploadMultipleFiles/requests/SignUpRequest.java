package com.project.UploadMultipleFiles.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignUpRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
