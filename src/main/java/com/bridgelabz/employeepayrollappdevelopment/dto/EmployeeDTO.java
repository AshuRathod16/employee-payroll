package com.bridgelabz.employeepayrollappdevelopment.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
public class EmployeeDTO {

    @NotNull(message = "FirstName Can't be Empty ")
    @Pattern(regexp = "[A-Z][a-z]{2,}", message = "Name Should Start with capital letter and min of 3 letter")
    private String firstName;
    @NotNull(message = "LastName Can't be Empty ")
    @Pattern(regexp = "[A-Z][a-z]{2,}", message = "Name Should Start with capital letter and min of 3 letter")
    private String lastName;
    @NotNull(message = "Age Can't be Empty ")
    @Min(value = 18, message = "Required minimum age is 18 ")
    private int age;
    @NotNull(message = "Salary Can't be Empty ")
    @Min(value = 1000, message = "Required minimum Salary is 1000")
    private long salary;
    @NotNull(message = "CompanyName Can't be Empty ")
    @Pattern(regexp = "[A-Z][a-zA-Z]{3,}", message = "Company name start with capital letter and minimum 3 letters")
    private String companyName;
    @NotNull(message = "Email-ID  Can't be Empty ")
    @Pattern(regexp = "(\\w+[.+-]?)*@\\w+(\\.+[a-zA-Z]{2,4})*", message = "Invalid Email, Enter correct Email")
    private String emailId;
    @NotNull(message = "Password Can't be Empty ")
    @Pattern(regexp = "(?=.*?[A-Z])(?=.*?\\d)(?=.*?[!@#$%^&*_()+-])[A-Za-z\\d!@#$%^&()*+_-]{8,}"
            , message = "Password should have AtLeast one (capital ,small,special character,numeric) minimum 8 characters")
    private String password;
}
