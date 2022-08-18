package com.bridgelabz.employeepayrollappdevelopment.service;

import com.bridgelabz.employeepayrollappdevelopment.dto.DepartmentDTO;
import com.bridgelabz.employeepayrollappdevelopment.exception.EmployeeNotFoundException;
import com.bridgelabz.employeepayrollappdevelopment.model.DepartmentModel;
import com.bridgelabz.employeepayrollappdevelopment.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService implements IDepartmentService{
    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public DepartmentModel addDepartment(DepartmentDTO departmentDTO) {
        DepartmentModel departmentModel = new DepartmentModel(departmentDTO);
        departmentModel.setCreatedTime(LocalDateTime.now());
        departmentRepository.save(departmentModel);
        return departmentModel;
    }

    @Override
    public DepartmentModel updateDepartment(DepartmentDTO departmentDTO, Long id) {
        Optional<DepartmentModel> isIdPresent = departmentRepository.findById(id);
        if (isIdPresent.isPresent()) {
            isIdPresent.get().setDepartmentName(departmentDTO.getDepartmentName());
            isIdPresent.get().setDepartmentDesc(departmentDTO.getDepartmentDesc());
            isIdPresent.get().setUpdatedTime(LocalDateTime.now());
            departmentRepository.save(isIdPresent.get());
            return isIdPresent.get();
        } else {
            throw new EmployeeNotFoundException(400, "Department is not found with this ID");
        }
    }

    @Override
    public List<DepartmentModel> getAllDepartments() {
        List<DepartmentModel> isDepartment = departmentRepository.findAll();
        if (isDepartment.size() > 0) {
            return isDepartment;
        } else {
            throw new EmployeeNotFoundException(400, "No Departments Found");
        }
    }

    @Override
    public DepartmentModel deleteDepartment(Long id) {
        Optional<DepartmentModel> isIdPresent = departmentRepository.findById(id);
        if (isIdPresent.isPresent()) {
            departmentRepository.delete(isIdPresent.get());
            return isIdPresent.get();
        } else {
            throw new EmployeeNotFoundException(400, "Department is not found");
        }
    }
}
