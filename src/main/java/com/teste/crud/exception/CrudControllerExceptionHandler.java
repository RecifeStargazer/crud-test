package com.teste.crud.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CrudControllerExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity validationErrorResponse(){
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity validationErrorNoClientResponse(){
        return ResponseEntity.badRequest().build();
    }
}
