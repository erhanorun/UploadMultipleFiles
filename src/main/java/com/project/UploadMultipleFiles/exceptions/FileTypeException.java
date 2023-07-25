package com.project.UploadMultipleFiles.exceptions;

import com.project.UploadMultipleFiles.responses.MessageResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FileTypeException extends ResponseEntityExceptionHandler {

    @Value(value = "${data.exception.message}")
    private String invalidMessage;

    @ExceptionHandler(FileTypeExceptionResponse.class)
    public ResponseEntity<MessageResponse> handleMaxSizeException() {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(invalidMessage));
    }
}