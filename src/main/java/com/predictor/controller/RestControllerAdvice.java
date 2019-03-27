package com.predictor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestControllerAdvice extends ResponseEntityExceptionHandler {


    private static final Logger LOGGER = LoggerFactory.getLogger(RestControllerAdvice.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Exception> handlerUnknownException(HttpServletRequest request, Exception ex) {
        LOGGER.info("Handler exception");
        return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
