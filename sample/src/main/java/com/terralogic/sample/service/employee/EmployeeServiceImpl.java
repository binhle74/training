package com.terralogic.sample.service.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.terralogic.sample.model.Employee;
import com.terralogic.sample.payload.EmployeeDTO;
import com.terralogic.sample.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		List<EmployeeDTO> employeesDto = new ArrayList<>();
		
		List<Employee> employees = employeeRepository.findAll();
		
		employeesDto = employees.stream().map(employee -> {
			EmployeeDTO dto = new EmployeeDTO(employee.getFirstName(), employee.getLastName(), employee.getEmail(),
					employee.getMobile(), employee.getHireDate());
			return dto;
		}).collect(Collectors.toList());
		
		return employeesDto;
	}
}
