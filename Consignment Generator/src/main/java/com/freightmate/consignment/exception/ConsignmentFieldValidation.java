package com.freightmate.consignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ConsignmentFieldValidation extends RuntimeException {

    public ConsignmentFieldValidation(String message) {
        super(message);
    }
}
