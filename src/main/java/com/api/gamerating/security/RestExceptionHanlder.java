package com.api.gamerating.security;

import com.api.gamerating.exceptions.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestExceptionHanlder {

    @ExceptionHandler(value = {AppException.class})
    @ResponseBody
    public ResponseEntity<ErrorDto> handleException(AppException ex){
        return ResponseEntity.status(ex.getCode())
                .body(ErrorDto.builder().message(ex.getMessage()).build());
    }

}
