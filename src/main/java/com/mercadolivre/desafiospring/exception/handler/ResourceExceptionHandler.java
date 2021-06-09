package com.mercadolivre.desafiospring.exception.handler;

import com.mercadolivre.desafiospring.exception.error.BadRequestException;
import com.mercadolivre.desafiospring.exception.error.NotFoundException;
import com.mercadolivre.desafiospring.exception.error.StandardError;
import com.mercadolivre.desafiospring.exception.error.UserSellerValidateRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> userNotFound(NotFoundException e, HttpServletRequest request){
        StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), LocalDateTime.now(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandardError> userBadRequest(BadRequestException e, HttpServletRequest request){
        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(UserSellerValidateRequestException.class)
    public ResponseEntity<StandardError> userValidate(UserSellerValidateRequestException e, HttpServletRequest request){
        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validateField(MethodArgumentNotValidException e, HttpServletRequest request){
        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(),
                e.getAllErrors().get(0).getDefaultMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> validateConstructor(HttpMessageNotReadableException e, HttpServletRequest request){
        String msg = "It's necessary include { 'field: value }";
        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(),
                msg +" " + e.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
