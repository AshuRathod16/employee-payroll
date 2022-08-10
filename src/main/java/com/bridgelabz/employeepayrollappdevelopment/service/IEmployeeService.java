package com.bridgelabz.employeepayrollappdevelopment.service;

import com.bridgelabz.employeepayrollappdevelopment.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollappdevelopment.model.EmployeeModel;

public interface IEmployeeService {
    EmployeeModel addEmployee(EmployeeDTO employeeDTO);
}
