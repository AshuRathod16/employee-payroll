package com.bridgelabz.employeepayrollappdevelopment.service;

import com.bridgelabz.employeepayrollappdevelopment.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollappdevelopment.model.EmployeeModel;
import com.bridgelabz.employeepayrollappdevelopment.util.Response;

import java.util.List;

public interface IEmployeeService {
    EmployeeModel addEmployee(EmployeeDTO employeeDTO);

    List<EmployeeModel> getAllEmployeeData(String token);

    EmployeeModel updateEmployeeDetails(Long id, EmployeeDTO employeeDTO, String toekn);

    EmployeeModel deleteEmployee(Long id, String token);

    Response login(String emailId, String password);
}
