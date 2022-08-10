package com.bridgelabz.employeepayrollappdevelopment.exception.exceptionhandler;

import com.bridgelabz.employeepayrollappdevelopment.exception.EmployeeNotFoundException;
import com.bridgelabz.employeepayrollappdevelopment.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Response> handlerHiringException(EmployeeNotFoundException he) {
        Response response = new Response();
        response.setErrorCode(400);
        response.setMessage(he.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
