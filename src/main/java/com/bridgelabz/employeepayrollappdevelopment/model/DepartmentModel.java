package com.bridgelabz.employeepayrollappdevelopment.model;

import com.bridgelabz.employeepayrollappdevelopment.dto.DepartmentDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "department")
@Data
public class DepartmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String departmentName;
    private String departmentDesc;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    public DepartmentModel() {
    }

    public DepartmentModel(DepartmentDTO departmentDTO) {
        this.departmentName = departmentDTO.getDepartmentName();
        this.departmentDesc = departmentDTO.getDepartmentDesc();
    }
}
