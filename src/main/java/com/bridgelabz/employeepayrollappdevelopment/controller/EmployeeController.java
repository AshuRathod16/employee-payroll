package com.bridgelabz.employeepayrollappdevelopment.controller;

import com.bridgelabz.employeepayrollappdevelopment.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollappdevelopment.model.EmployeeModel;
import com.bridgelabz.employeepayrollappdevelopment.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeepayroll")
public class EmployeeController {
    @Autowired
    IEmployeeService employeeService;

    @PostMapping("/addemployee")
    public EmployeeModel addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.addEmployee(employeeDTO);
    }

    @GetMapping("/getallemployee")
    public List<EmployeeModel> getAllEmployeeData() {
        return employeeService.getAllEmployeeData();
    }
}

