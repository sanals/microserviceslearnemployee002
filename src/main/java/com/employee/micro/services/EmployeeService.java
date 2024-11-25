package com.employee.micro.services;

import com.employee.micro.view.AddressView;
import com.employee.micro.view.EmployeeView;

public interface EmployeeService {
	public EmployeeView getEmployeeDetails(Integer id) throws Exception;

	public EmployeeView getEmployeeDetailsModelMapper(Integer id) throws Exception;
}
