package com.ssafy.newstar.util.response.exception;

import com.ssafy.newstar.util.response.ErrorResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
  @ExceptionHandler(GlobalException.class)
  public ResponseEntity<ErrorResponseEntity> ExceptionHandler(GlobalException e) {
    return ErrorResponseEntity.toResponseEntity(e.getErrorCode());
  }
}
