package com.bridgelabz.employeepayrollappdevelopment.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Response {
    private int errorCode;
    private String message;

    public Response() {
    }
}
