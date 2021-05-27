package com.example.taxiservice.controller;

import com.example.taximodel.dto.response.ErrorDTO;
import com.example.taxiservice.exception.TaxiIdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseController {

    @ExceptionHandler(TaxiIdNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleTaxiIdNotFoundException(TaxiIdNotFoundException e) {
        return new ResponseEntity<ErrorDTO>(new ErrorDTO(e.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }
}
