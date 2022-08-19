package com.bridgelabz.employeepayrollappdevelopment.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class DepartmentDTO {
    @NotNull
    @Pattern(regexp = "[A-Z][a-zA-Z]{3,}", message = "Invalid Department Name, Check Once")
    private String departmentName;
    @NotNull
    @Pattern(regexp = "[A-Z][a-zA-Z]{3,}", message = "Invalid Department Description, Check Once")
    private String departmentDesc;
}
