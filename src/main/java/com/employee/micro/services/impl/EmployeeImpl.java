package com.employee.micro.services.impl;

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

//	@Autowired
	private final RestTemplate restTemplate; //RestTemplate no longer needs @Autowired since it is added in constructor

//	@Value("${address.service.base.url}") //moved to EmployeeImpl constructor
//	private String addressServiceBaseUrl;

	@Autowired
	private WebClient webClient;

	public EmployeeImpl(@Value("${address.service.base.url}") String addressServiceBaseUrl, RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder
				.rootUri(addressServiceBaseUrl)
				.build();
	}

	@Override
	public EmployeeView getEmployeeDetails(Integer id) throws Exception {
		Employee employee = employeeRepo.findById(id).orElseThrow(()-> new Exception("Unable to fetch Employee"));
		return new EmployeeView(employee, fetchAddressWithWebClientForEmployee(id));
	}
	
	@Override
	public EmployeeView getEmployeeDetailsModelMapper(Integer id) throws Exception {
		Employee employee = employeeRepo.findById(id).orElseThrow(()-> new Exception("Unable to fetch Employee"));
		EmployeeView employeeView = modelMapper.map(employee, EmployeeView.class);
		employeeView.setAddress(fetchAddressWithWebClientForEmployee(id));
		return employeeView;
	}

	@Override
	public AddressView fetchAddressWithRestTemplateForEmployee(Integer id) throws Exception {
		return restTemplate.getForObject("/address/{id}", AddressView.class, id);
	}

	@Override
	public AddressView fetchAddressWithWebClientForEmployee(Integer id) throws Exception {
		return webClient
				.get()
				.uri("/address/{id}",id)
				.retrieve()
				.bodyToMono(AddressView.class)
				.block();
	}

}
