package com.bridgelabz.employeepayrollappdevelopment.service;


import com.bridgelabz.employeepayrollappdevelopment.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollappdevelopment.exception.EmployeeNotFoundException;
import com.bridgelabz.employeepayrollappdevelopment.model.EmployeeModel;
import com.bridgelabz.employeepayrollappdevelopment.repository.EmployeeRepository;
import com.bridgelabz.employeepayrollappdevelopment.util.Response;
import com.bridgelabz.employeepayrollappdevelopment.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    MailService mailService;

    @Override
    public EmployeeModel addEmployee(EmployeeDTO employeeDTO) {
        EmployeeModel employeeModel = new EmployeeModel(employeeDTO);
        employeeModel.setRegisterDate(LocalDateTime.now());
        employeeRepository.save(employeeModel);
        String body = "Employee added Successfully with Employee id is :" + employeeModel.getId();
        String subject = "Employee Registration Successfully";
        mailService.send(employeeDTO.getEmailId(), body, subject);
        return employeeModel;
    }

    @Override
    public List<EmployeeModel> getAllEmployeeData(String token) {
        Long empId = tokenUtil.decodeToken(token);
        Optional<EmployeeModel> isEmployeePresent = employeeRepository.findById(empId);
        if (isEmployeePresent.isPresent()) {
            List<EmployeeModel> getEmployee = employeeRepository.findAll();
            if (getEmployee.size() > 0) {
                return getEmployee;
            } else {
                throw new EmployeeNotFoundException(400, "No Employee Is There");
            }
        }
        throw new EmployeeNotFoundException(400, "Employee Not Found");
    }


    @Override
    public EmployeeModel updateEmployeeDetails(Long id, EmployeeDTO employeeDTO, String token) {
        Long empId = tokenUtil.decodeToken(token);
        Optional<EmployeeModel> isEmpPresent = employeeRepository.findById(empId);
        if (isEmpPresent.isPresent()) {
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
                String body = "Employee Updated Successfully with Employee id is :" + isEmployeePresent.get().getId();
                String subject = "Employee Updated Successfully..";
                mailService.send(employeeDTO.getEmailId(), body, subject);
                return isEmployeePresent.get();
            } else {
                throw new EmployeeNotFoundException(400, "Employee is Not Found");
            }
        }
        throw new EmployeeNotFoundException(400, "Wrong token");
    }

    @Override
    public EmployeeModel deleteEmployee(Long id, String token) {
        Long empId = tokenUtil.decodeToken(token);
        Optional<EmployeeModel> isEmpPresent = employeeRepository.findById(empId);
        if (isEmpPresent.isPresent()) {
            Optional<EmployeeModel> deleteEmployee = employeeRepository.findById(id);
            if (deleteEmployee.isPresent()) {
                employeeRepository.delete(deleteEmployee.get());
                String body = "Employee Deleted Successfully with Employee id is :" + isEmpPresent.get().getId();
                String subject = "Employee Deleted..";
                mailService.send(isEmpPresent.get().getEmailId(), body, subject);
                return deleteEmployee.get();
            } else {
                throw new EmployeeNotFoundException(400, "Employee is Not Found");
            }
        }
        throw new EmployeeNotFoundException(400, "Token is wrong");
    }

    @Override
    public Response login(String emailId, String password) {
        Optional<EmployeeModel> isEmailPresent = employeeRepository.findByEmailId(emailId);
        if (isEmailPresent.isPresent()) {
            if (isEmailPresent.get().getPassword().equals(password)) {
                String token = tokenUtil.createToken(isEmailPresent.get().getId());
                return new Response(200, "LoginSuccess", token);
            } else {
                throw new EmployeeNotFoundException(400, "Password is wrong");
            }
        }
        throw new EmployeeNotFoundException(400, "Employee is not found");
    }

}
