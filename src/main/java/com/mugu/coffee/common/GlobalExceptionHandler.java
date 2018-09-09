package com.mugu.coffee.common;

import com.mugu.coffee.model.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<Status> handleAll(Exception e) {
        log.error("Unknown Exception occurred :: " + e.getMessage(), e);
        return new ResponseEntity<Status>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
