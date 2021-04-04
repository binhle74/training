package com.terralogic.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.terralogic.sample.payload.EmployeeDTO;
import com.terralogic.sample.service.employee.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    
    @GetMapping("/all")
    public List<EmployeeDTO> getEmployees() {
        return employeeService.getAllEmployees();
    }
}
