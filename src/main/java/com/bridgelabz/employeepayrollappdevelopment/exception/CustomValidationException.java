package com.bridgelabz.employeepayrollappdevelopment.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomValidationException {
    private int errorCode;
    private String message;
}
