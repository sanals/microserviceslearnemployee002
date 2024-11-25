package com.employee.micro.services.impl;

import com.employee.micro.feignclient.AddressFeignClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.employee.micro.entity.Employee;
import com.employee.micro.repository.EmployeeRepo;
import com.employee.micro.services.EmployeeService;
import com.employee.micro.view.AddressView;
import com.employee.micro.view.EmployeeView;

@Service
public class EmployeeImpl implements EmployeeService{

	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Value("${address.service.base.url}") //can be moved to EmployeeImpl constructor
	private String addressServiceBaseUrl;

	@Autowired
	AddressFeignClient addressFeignClient;

	@Override
	public EmployeeView getEmployeeDetails(Integer id) throws Exception {
		Employee employee = employeeRepo.findById(id).orElseThrow(()-> new Exception("Unable to fetch Employee"));
		return new EmployeeView(employee, addressFeignClient.getAddressByEmployeeId(id));
	}
	
	@Override
	public EmployeeView getEmployeeDetailsModelMapper(Integer id) throws Exception {
		Employee employee = employeeRepo.findById(id).orElseThrow(()-> new Exception("Unable to fetch Employee"));
		EmployeeView employeeView = modelMapper.map(employee, EmployeeView.class);

		employeeView.setAddress(addressFeignClient.getAddressByEmployeeId(id));
		return employeeView;
	}

}
