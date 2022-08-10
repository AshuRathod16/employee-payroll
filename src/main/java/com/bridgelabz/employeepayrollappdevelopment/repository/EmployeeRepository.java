package com.bridgelabz.employeepayrollappdevelopment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bridgelabz.employeepayrollappdevelopment.model.EmployeeModel;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {
    Optional<EmployeeModel> findByEmailId(String emailId);

}
