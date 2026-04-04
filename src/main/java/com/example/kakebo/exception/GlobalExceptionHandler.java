package com.example.kakebo.exception;

import java.util.Map;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleBadRequest(IllegalArgumentException e){
        return ResponseEntity.status(400).body(
                Map.of(
                        "status",400,
                        "code",e.getMessage()
                )
            );           
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException e){

        String code=e.getBindingResult()
                     .getFieldErrors()
                     .get(0)
                     .getDefaultMessage();
        
        return ResponseEntity.status(400).body(
                Map.of("status",400,
                    "code", code
                )
        );
    }


    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<?> handleDuplicate(DuplicateKeyException e){
        String code="DUPLICATE_ERROR";

        if(e.getMessage() != null && e.getMessage().contains("users.username")){
            code="USERNAME_ALREADY_EXISTS";
        }

        return ResponseEntity.status(409).body(
                Map.of(
                        "status",409,
                        "code",code
                )
            );

    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<?> handleForbidden(ForbiddenException e){
        return ResponseEntity.status(403).body(
            Map.of("status",403,
                   "code",e.getMessage()

        )
    );
    }

    @ExceptionHandler(org.springframework.dao.EmptyResultDataAccessException.class)
    public ResponseEntity<?> handleNotFound(org.springframework.dao.EmptyResultDataAccessException e){
        return ResponseEntity.status(404).body(
                Map.of(
                    "status",404,
                    "code","NOT_FOUND"
                )
            );
        
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleOther(Exception e){
        
        return ResponseEntity.status(500).body(
                Map.of(
                        "status",500,
                        "code","INTERNAL_SERVER_ERROR"

                )
            );
    }




}
