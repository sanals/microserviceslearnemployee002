package com.employee.micro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.employee.micro.services.EmployeeService;
import com.employee.micro.view.EmployeeView;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;

	@GetMapping("/employees/{id}")
	public ResponseEntity<EmployeeView> getEmployees(@PathVariable("id") Integer id) throws Exception {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeDetails(id));
	}
}
