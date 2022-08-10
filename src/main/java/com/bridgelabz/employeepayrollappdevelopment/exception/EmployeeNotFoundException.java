package com.bridgelabz.employeepayrollappdevelopment.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class EmployeeNotFoundException extends RuntimeException{
    private int statusCode;
    private String statusMessage;

    public EmployeeNotFoundException(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

}
