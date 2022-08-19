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
    public EmployeeModel addEmployee(@RequestBody EmployeeDTO employeeDTO,
                                     @RequestParam Long departmentId) {
        return employeeService.addEmployee(employeeDTO, departmentId);
    }

    @GetMapping("/getallemployee")
    public List<EmployeeModel> getAllEmployeeData(@RequestHeader String token) {
        return employeeService.getAllEmployeeData(token);
    }

    @PutMapping("/updateemployee/{id}")
    public EmployeeModel updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO
            , @RequestHeader String token, @RequestParam Long departmentId) {
        return employeeService.updateEmployeeDetails(id, employeeDTO, token, departmentId);
    }

    @DeleteMapping("/deleteemployee/{id}")
    public EmployeeModel deleteEmployee(@PathVariable Long id, @RequestHeader String token) {
        return employeeService.deleteEmployee(id, token);
    }

    @PostMapping("/login")
    public Response login(@RequestParam String emailId, @RequestParam String password) {
        return employeeService.login(emailId, password);
    }

    @GetMapping("/sortingByFirstName")
    public List<EmployeeModel> sortingByFirstName() {
        return employeeService.sorting();
    }

    @GetMapping("/findByCompanyName")
    public List<EmployeeModel> findByCompanyName(@RequestParam String companyName) {
        return employeeService.findByCompanyName(companyName);
    }

    @GetMapping("/findByFirstName")
    public List<EmployeeModel> findByName(@RequestParam String firstName) {
        return employeeService.findByFirstName(firstName);
    }

    @GetMapping("/orderByLatName")
    public List<EmployeeModel> orderByLastName() {
        return employeeService.orderByLastName();
    }
}

