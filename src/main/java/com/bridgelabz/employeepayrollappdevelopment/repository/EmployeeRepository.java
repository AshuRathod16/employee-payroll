package com.bridgelabz.employeepayrollappdevelopment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bridgelabz.employeepayrollappdevelopment.model.EmployeeModel;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, Long> {
    Optional<EmployeeModel> findByEmailId(String emailId);

    @Query(value = "select * from employee where first_name=:firstName", nativeQuery = true)
    List<EmployeeModel> findFirstName(String firstName);

    List<EmployeeModel> findByCompanyNameContainingIgnoreCase(String companyName);

    @Query(value = "select * from employee order by last_name  ", nativeQuery = true)
    List<EmployeeModel> orderByLastName();

}
