package com.bridgelabz.employeepayrollappdevelopment.controller;

import com.bridgelabz.employeepayrollappdevelopment.dto.DepartmentDTO;
import com.bridgelabz.employeepayrollappdevelopment.model.DepartmentModel;
import com.bridgelabz.employeepayrollappdevelopment.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    IDepartmentService departmentService;

    @PostMapping("/addDepartment")
    public DepartmentModel addDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
        return departmentService.addDepartment(departmentDTO);
    }

    @PutMapping("/updateDepartment/{id}")
    public DepartmentModel updateDepartment(@RequestBody DepartmentDTO departmentDTO, @PathVariable Long id) {
        return departmentService.updateDepartment(departmentDTO, id);
    }

    @GetMapping("/getAllDepartments")
    public List<DepartmentModel> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @DeleteMapping("/deleteDepartment/{id}")
    public DepartmentModel deleteDepartment(@PathVariable Long id) {
        return departmentService.deleteDepartment(id);
    }
}
