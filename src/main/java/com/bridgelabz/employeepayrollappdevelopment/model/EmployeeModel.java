package com.bridgelabz.employeepayrollappdevelopment.model;

import com.bridgelabz.employeepayrollappdevelopment.dto.EmployeeDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "employee")
@Data
public class EmployeeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private long salary;
    private String companyName;
    private String emailId;
    private String password;
    private LocalDateTime registerDate;
    private LocalDateTime updatedDate;
    @OneToOne
    private DepartmentModel department;

    public EmployeeModel(EmployeeDTO employeeDTO) {
        this.firstName = employeeDTO.getFirstName();
        this.lastName = employeeDTO.getLastName();
        this.age = employeeDTO.getAge();
        this.salary = employeeDTO.getSalary();
        this.companyName = employeeDTO.getCompanyName();
        this.emailId = employeeDTO.getEmailId();
        this.password = employeeDTO.getPassword();
    }

    public EmployeeModel() {

    }
}
