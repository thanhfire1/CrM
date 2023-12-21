package com.example.baitapcyber.Repository;

import com.example.baitapcyber.Entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface EmployeesRepository extends JpaRepository<Employees,Integer> {
    List<Employees>findByEmployeeNameAndSalary(String name,double salary);
}
