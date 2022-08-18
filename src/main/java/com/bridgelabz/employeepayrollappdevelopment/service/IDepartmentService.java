package com.bridgelabz.employeepayrollappdevelopment.service;

import com.bridgelabz.employeepayrollappdevelopment.dto.DepartmentDTO;
import com.bridgelabz.employeepayrollappdevelopment.model.DepartmentModel;

import java.util.List;

public interface IDepartmentService {
    DepartmentModel addDepartment(DepartmentDTO departmentDTO);

    DepartmentModel updateDepartment(DepartmentDTO departmentDTO, Long id);

    List<DepartmentModel> getAllDepartments();

    DepartmentModel deleteDepartment(Long id);
}
