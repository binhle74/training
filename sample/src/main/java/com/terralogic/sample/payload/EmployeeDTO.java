package com.terralogic.sample.payload;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter 
@Getter
@ToString
public class EmployeeDTO {
    private Long id;
    
    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private String mobile;
    
    private Date hireDate;
}
