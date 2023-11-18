package com.challengedbackend.challengedbackend.Controller;


import java.util.HashMap;
import java.util.Map;

import com.challengedbackend.challengedbackend.Exceptions.Error;
import com.challengedbackend.challengedbackend.Exceptions.GeneralException;
import com.challengedbackend.challengedbackend.Exceptions.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Component
public class ControllerAdvice {

    @ExceptionHandler(value = GeneralException.class)
    public ResponseEntity<Error> requestExceptionHandler(GeneralException ex) {
        Error error = Error.builder().message(ex.getMessage()).build();
        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> handleNotfoundException(NoHandlerFoundException ex) {
        return ResponseEntity
                .status(ex.getStatusCode())
                .body(new Message(
                        "Lo siento, no se pudo encontrar el recurso solicitado."));
    }
}