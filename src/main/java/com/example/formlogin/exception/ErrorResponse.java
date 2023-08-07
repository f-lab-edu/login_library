package com.example.formlogin.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorResponse {

    private String errorContent;

    private int statusCode;

    private String errorMessage;

    public ErrorResponse(HttpStatus httpStatus, String errorMessage) {
        this.errorContent = httpStatus.getReasonPhrase();
        this.statusCode = httpStatus.value();
        this.errorMessage = errorMessage;
    }
}
