package com.tallyto.gestur.handler;


import com.tallyto.gestur.exception.BadRequestException;
import com.tallyto.gestur.exception.ExceptionResponse;
import com.tallyto.gestur.exception.ForbiddenException;
import com.tallyto.gestur.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request){
        return new ResponseEntity<>(
                ExceptionResponse.builder()
                        .timestamp(new Date())
                        .message(ex.getMessage())
                        .details(request.getDescription(false))
                        .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(Exception ex, WebRequest request){
        return new ResponseEntity<>(
                ExceptionResponse.builder()
                        .timestamp(new Date())
                        .message(ex.getMessage())
                        .details(request.getDescription(false))
                        .build(),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestException(Exception ex, WebRequest request){
        return new ResponseEntity<>(
                ExceptionResponse.builder()
                        .timestamp(new Date())
                        .message(ex.getMessage())
                        .details(request.getDescription(false))
                        .build(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ForbiddenException.class)
    public final ResponseEntity<ExceptionResponse> handleForbiddenException(Exception ex, WebRequest request){
        return new ResponseEntity<>(
                ExceptionResponse.builder()
                        .timestamp(new Date())
                        .message(ex.getMessage())
                        .details(request.getDescription(false))
                        .build(),HttpStatus.FORBIDDEN);
    }
}
