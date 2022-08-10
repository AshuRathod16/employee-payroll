package com.bridgelabz.employeepayrollappdevelopment.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Response {
    private int errorCode;
    private String message;
    private Object token;
    public Response() {
    }
}
