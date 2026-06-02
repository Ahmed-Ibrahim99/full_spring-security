package com.amaghrabi.Security.exception;

import com.amaghrabi.Security.model.AppError;
import com.amaghrabi.Security.service.AppErrorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final AppErrorService appErrorService;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        AppError error = appErrorService.getByName(ex.getErrorName());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(error.getCode(),
                        error.getName(), error.getDescription()));
    }
}
