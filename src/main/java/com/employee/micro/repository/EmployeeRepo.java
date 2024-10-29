package com.employee.micro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.micro.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

}
