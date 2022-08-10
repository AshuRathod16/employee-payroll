package com.bridgelabz.employeepayrollappdevelopment.service;


import com.bridgelabz.employeepayrollappdevelopment.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollappdevelopment.exception.EmployeeNotFoundException;
import com.bridgelabz.employeepayrollappdevelopment.model.EmployeeModel;
import com.bridgelabz.employeepayrollappdevelopment.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public EmployeeModel addEmployee(EmployeeDTO employeeDTO) {
        EmployeeModel employeeModel = new EmployeeModel(employeeDTO);
        employeeModel.setRegisterDate(LocalDateTime.now());
        employeeRepository.save(employeeModel);
        return employeeModel;
    }

    @Override
    public List<EmployeeModel> getAllEmployeeData(){
        List<EmployeeModel> getEmployee = employeeRepository.findAll();
        if (getEmployee.size() > 0){
            return getEmployee;
        } else {
            throw new EmployeeNotFoundException(400, "Employee Not Found");
        }
    }
}
