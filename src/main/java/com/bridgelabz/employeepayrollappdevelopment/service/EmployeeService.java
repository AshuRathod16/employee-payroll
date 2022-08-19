package com.bridgelabz.employeepayrollappdevelopment.service;


import com.bridgelabz.employeepayrollappdevelopment.dto.EmployeeDTO;
import com.bridgelabz.employeepayrollappdevelopment.exception.EmployeeNotFoundException;
import com.bridgelabz.employeepayrollappdevelopment.model.DepartmentModel;
import com.bridgelabz.employeepayrollappdevelopment.model.EmployeeModel;
import com.bridgelabz.employeepayrollappdevelopment.repository.DepartmentRepository;
import com.bridgelabz.employeepayrollappdevelopment.repository.EmployeeRepository;
import com.bridgelabz.employeepayrollappdevelopment.util.Response;
import com.bridgelabz.employeepayrollappdevelopment.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public EmployeeModel addEmployee(EmployeeDTO employeeDTO, Long departmentId) {
        Optional<DepartmentModel> isDepartment = departmentRepository.findById(departmentId);
        if (isDepartment.isPresent()) {
            EmployeeModel employeeModel = new EmployeeModel(employeeDTO);
            employeeModel.setDepartment(isDepartment.get());
            employeeModel.setRegisterDate(LocalDateTime.now());
            employeeRepository.save(employeeModel);
            String body = "Employee added Successfully with Employee id is :" + employeeModel.getId();
            String subject = "Employee Registration Successfully";
            mailService.send(employeeDTO.getEmailId(), body, subject);
            return employeeModel;
        } else {
            throw new EmployeeNotFoundException(400, "Department is not found");
        }
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
    public EmployeeModel updateEmployeeDetails(Long id, EmployeeDTO employeeDTO, String token, Long departmentId) {
        Long empId = tokenUtil.decodeToken(token);
        Optional<DepartmentModel> isDepartment = departmentRepository.findById(departmentId);
        if (isDepartment.isPresent()) {
            Optional<EmployeeModel> isEmpPresent = employeeRepository.findById(empId);
            if (isEmpPresent.isPresent()) {
                Optional<EmployeeModel> isEmployeePresent = employeeRepository.findById(id);
                if (isEmployeePresent.isPresent()) {
                    isEmployeePresent.get().setFirstName(employeeDTO.getFirstName());
                    isEmployeePresent.get().setLastName(employeeDTO.getLastName());
                    isEmployeePresent.get().setAge(employeeDTO.getAge());
                    isEmployeePresent.get().setSalary(employeeDTO.getSalary());
                    isEmployeePresent.get().setCompanyName(employeeDTO.getCompanyName());
                    isEmployeePresent.get().setUpdatedDate(LocalDateTime.now());
                    isEmployeePresent.get().setDepartment(isDepartment.get());
                    employeeRepository.save(isEmployeePresent.get());
                    String body = "Employee Updated Successfully with Employee id is :" + isEmployeePresent.get().getId();
                    String subject = "Employee Updated Successfully..";
                    mailService.send(employeeDTO.getEmailId(), body, subject);
                    return isEmployeePresent.get();
                } else {
                    throw new EmployeeNotFoundException(400, "Employee Not Found");
                }
            }
            throw new EmployeeNotFoundException(400, "Invalid Token");
        }
        throw new EmployeeNotFoundException(400, "Department is not found with this ID");
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
                throw new EmployeeNotFoundException(400, "Employee Not Found");
            }
        }
        throw new EmployeeNotFoundException(400, "Invalid Token");
    }

    @Override
    public Response login(String emailId, String password) {
        Optional<EmployeeModel> isEmailPresent = employeeRepository.findByEmailId(emailId);
        if (isEmailPresent.isPresent()) {
            if (isEmailPresent.get().getPassword().equals(password)) {
                String token = tokenUtil.createToken(isEmailPresent.get().getId());
                return new Response(200, "LoginSuccessful", token);
            } else {
                throw new EmployeeNotFoundException(400, "Invalid Password");
            }
        }
        throw new EmployeeNotFoundException(400, "Employee Not found");
    }

    @Override
    public List<EmployeeModel> sorting() {
        List<EmployeeModel> sorting = employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
        if (sorting.size() > 0) {
            return sorting;
        } else {
            throw new EmployeeNotFoundException(400, "Employees Not Found");
        }
    }

    @Override
    public List<EmployeeModel> findByCompanyName(String companyName) {
        List<EmployeeModel> isCompany = employeeRepository.findByCompanyNameContainingIgnoreCase(companyName);
        if (isCompany.size() > 0) {
            return isCompany;
        } else {
            throw new EmployeeNotFoundException(400, "Company Not Found");
        }
    }


    @Override
    public List<EmployeeModel> findByFirstName(String firstName) {
        List<EmployeeModel> isFirstName = employeeRepository.findFirstName(firstName);
        if (isFirstName.isEmpty()) {
            throw new EmployeeNotFoundException(400, "Employee Not Found");
        } else {
            return isFirstName;
        }
    }

    @Override
    public List<EmployeeModel> orderByLastName() {
        List<EmployeeModel> isLastName = employeeRepository.orderByLastName();
        if (isLastName.size() > 0) {
            return isLastName;
        } else {
            throw new EmployeeNotFoundException(400, "Employee Not found");
        }
    }

}
