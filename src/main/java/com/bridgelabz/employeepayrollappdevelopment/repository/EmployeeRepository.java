package com.bridgelabz.employeepayrollappdevelopment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bridgelabz.employeepayrollappdevelopment.model.EmployeeModel;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {

}
