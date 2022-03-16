package com.frontrow.springapp.exception.advice;

import com.frontrow.springapp.exception.ResourceNotFoundException;
import com.frontrow.springapp.pojo.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApiExceptionAdvice {

  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse resourceNotFoundException(ResourceNotFoundException e) {
    return ErrorResponse.builder()
        .code(HttpStatus.NOT_FOUND.value())
        .message(e.getMessage())
        .build();
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse internalServerError(Exception e, WebRequest request) {
    return ErrorResponse.builder()
        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .message("Unexpected Internal Server Error")
        .build();
  }
}
