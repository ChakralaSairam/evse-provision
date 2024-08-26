package com.sampleProject.evse.provision.advice;


import com.mongodb.MongoWriteException;
import com.sampleProject.evse.provision.exception.DuplicateValueException;
import com.sampleProject.evse.provision.exception.ErrorDetails;
import com.sampleProject.evse.provision.exception.EvseNotFoundException;
import com.sampleProject.evse.provision.exception.SiteNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//
//@Order(Ordered.HIGHEST_PRECEDENCE)


@RestControllerAdvice
public class ApplicationExceptionHandler {


    @ExceptionHandler(ResponseStatusException.class)
    public ErrorDetails handleResponseStatusException(ResponseStatusException ex, WebRequest request){
        ErrorDetails error = new ErrorDetails(LocalDate.now(), ex.getStatusCode(), ex.getReason(), request.getDescription(false),ex.getStatusCode().value());
        return  error;
    }


    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorDetails> handleNoResourceFoundException(NoResourceFoundException ex,WebRequest request){
        ErrorDetails error = new ErrorDetails(LocalDate.now(), HttpStatus.INTERNAL_SERVER_ERROR, "API request cannot be processed", request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDetails handleAllDefaultException(Exception ex, WebRequest request) {
        return new ErrorDetails(LocalDate.now(), HttpStatus.INTERNAL_SERVER_ERROR, "Error Occurred is: " + ex.getMessage(), request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }


    @ExceptionHandler(SiteNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleSiteNotFoundException(SiteNotFoundException ex, WebRequest request){
        ErrorDetails error = new ErrorDetails(LocalDate.now(), HttpStatus.NOT_FOUND, ex.getMessage(), request.getDescription(false),HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EvseNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleEvseNotFoundException(EvseNotFoundException ex, WebRequest request){
        ErrorDetails error = new ErrorDetails(LocalDate.now(), HttpStatus.NOT_FOUND, ex.getMessage(), request.getDescription(false),HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,ErrorDetails>> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        BindingResult result = ex.getBindingResult();
        Map<String,ErrorDetails> errorDetailsMap = new HashMap<>();
        result.getAllErrors().forEach(error -> {
            ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),HttpStatus.UNPROCESSABLE_ENTITY,error.getDefaultMessage(),
                    request.getDescription(false), HttpStatus.UNPROCESSABLE_ENTITY.value());
            errorDetailsMap.put(((FieldError)error).getField(), errorDetails);
        });

        return ResponseEntity.unprocessableEntity().body(errorDetailsMap);
    }


//    @ExceptionHandler(MongoWriteException.class)
//    public ResponseEntity<Object> handleMongoWriteException(MongoWriteException ex,WebRequest request) {
//        ErrorDetails error = new ErrorDetails(LocalDate.now(), HttpStatus.INTERNAL_SERVER_ERROR, "Error: Duplicate entity detected",
//                request.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR.value());
//        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(DuplicateValueException.class)
    public ResponseEntity<Object> handleDuplicateValueException(DuplicateValueException ex,WebRequest request) {
        ErrorDetails error = new ErrorDetails(LocalDate.now(), HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(),
                request.getDescription(false),ex.getErrorCode());
        return ResponseEntity.internalServerError().body(error);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//        BindingResult result = ex.getBindingResult();
//        Map<String,ErrorDetails> errorDetailsMap = new HashMap<>();
//        result.getAllErrors().forEach(error -> {
//            ErrorDetails errorDetails = new ErrorDetails(LocalDate.now(),HttpStatus.UNPROCESSABLE_ENTITY,error.getDefaultMessage(),
//                    request.getDescription(false), HttpStatus.UNPROCESSABLE_ENTITY.value());
//            errorDetailsMap.put(((FieldError)error).getField(), errorDetails);
//        });
//
//        return ResponseEntity.unprocessableEntity().body(errorDetailsMap);
//    }


}
