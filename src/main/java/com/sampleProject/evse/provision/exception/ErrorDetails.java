package com.sampleProject.evse.provision.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDate;


@Getter@Setter
@NoArgsConstructor
public class ErrorDetails {
    private LocalDate timeStamp;
    private HttpStatusCode status;
    private String message;
    private String path;
    private int errorCode;

    public ErrorDetails(LocalDate now, HttpStatus httpStatus, String message, int errorCode) {
        this.timeStamp = now;
        this.status = httpStatus;
        this.message = message;
        this.errorCode = errorCode;
    }

    public ErrorDetails(LocalDate now, HttpStatusCode httpStatusCode, String message, String path, int errorCode) {
        this.timeStamp = now;
        this.status = httpStatusCode;
        this.message = message;
        this.path = path;
        this.errorCode = errorCode;
    }
}
