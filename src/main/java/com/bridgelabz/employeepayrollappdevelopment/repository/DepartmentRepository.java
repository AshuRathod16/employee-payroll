package com.bridgelabz.employeepayrollappdevelopment.repository;

import com.bridgelabz.employeepayrollappdevelopment.model.DepartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentModel, Long> {
}
