package com.bridgelabz.employeepayrollappdevelopment.service;


import com.bridgelabz.employeepayrollappdevelopment.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollappdevelopment.exception.EmployeeNotFoundException;
import com.bridgelabz.employeepayrollappdevelopment.model.EmployeeModel;
import com.bridgelabz.employeepayrollappdevelopment.repository.EmployeeRepository;
import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    public List<EmployeeModel> getAllEmployeeData() {
        List<EmployeeModel> getEmployee = employeeRepository.findAll();
        if (getEmployee.size() > 0) {
            return getEmployee;
        } else {
            throw new EmployeeNotFoundException(400, "Employee Not Found");
        }
    }

    @Override
    public EmployeeModel updateEmployeeDetails(Long id, EmployeeDTO employeeDTO) {
        Optional<EmployeeModel> isEmployeePresent = employeeRepository.findById(id);
        if (isEmployeePresent.isPresent()) {
            isEmployeePresent.get().setFirstName(employeeDTO.getFirstName());
            isEmployeePresent.get().setLastName(employeeDTO.getLastName());
            isEmployeePresent.get().setAge(employeeDTO.getAge());
            isEmployeePresent.get().setSalary(employeeDTO.getSalary());
            isEmployeePresent.get().setDepartment(employeeDTO.getDepartment());
            isEmployeePresent.get().setCompanyName(employeeDTO.getCompanyName());
            isEmployeePresent.get().setUpdatedDate(LocalDateTime.now());
            employeeRepository.save(isEmployeePresent.get());
            return isEmployeePresent.get();
        } else {
            throw new EmployeeNotFoundException(400, "Employee is Not Found");
        }
    }
}
