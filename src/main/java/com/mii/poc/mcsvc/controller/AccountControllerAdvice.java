package com.mii.poc.mcsvc.controller;

import com.mii.poc.mcsvc.RestResponse;
import com.mii.poc.mcsvc.util.DataNotFoundException;
import com.mii.poc.mcsvc.util.DuplicateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author ErwinSn
 */
@ControllerAdvice
public class AccountControllerAdvice {

    @ResponseBody
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<RestResponse> dataNotFoundHandler(DataNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(RestResponse.failed(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
    }
    
    @ResponseBody
    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ResponseEntity<RestResponse> dataDuplicateHandler(DuplicateException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(RestResponse.failed(ex.getMessage(), HttpStatus.CONFLICT.value()));
    }

}
