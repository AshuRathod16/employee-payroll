package com.bridgelabz.employeepayrollappdevelopment.controller;

import com.bridgelabz.employeepayrollappdevelopment.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollappdevelopment.model.EmployeeModel;
import com.bridgelabz.employeepayrollappdevelopment.service.IEmployeeService;
import com.bridgelabz.employeepayrollappdevelopment.util.Response;
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
    public List<EmployeeModel> getAllEmployeeData(@RequestHeader String token) {
        return employeeService.getAllEmployeeData(token);
    }

    @PutMapping("/updateemployee/{id}")
    public EmployeeModel updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployeeDetails(id, employeeDTO);
    }

    @DeleteMapping("/deleteemployee/{id}")
    public EmployeeModel deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }

    @PostMapping("/login")
    public Response login(@RequestParam String emailId, @RequestParam String password) {
        return employeeService.login(emailId, password);
    }
}