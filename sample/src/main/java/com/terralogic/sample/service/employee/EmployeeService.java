package com.terralogic.sample.service.employee;

import java.util.List;

import org.springframework.stereotype.Service;

import com.terralogic.sample.payload.EmployeeDTO;

@Service
public interface EmployeeService {

    List<EmployeeDTO> getAllEmployees();

}
