package com.bridgelabz.employeepayrollappdevelopment.model;

import com.bridgelabz.employeepayrollappdevelopment.dto.EmployeeDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Employee")
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
    private String department;
    private LocalDateTime registerDate;
    private LocalDateTime updatedDate;

    public EmployeeModel(EmployeeDTO employeeDTO) {
        this.firstName = employeeDTO.getFirstName();
        this.lastName = employeeDTO.getLastName();
        this.age = employeeDTO.getAge();
        this.salary = employeeDTO.getAge();
        this.companyName = employeeDTO.getCompanyName();
        this.department = employeeDTO.getDepartment();
    }

    public EmployeeModel() {

    }
}
