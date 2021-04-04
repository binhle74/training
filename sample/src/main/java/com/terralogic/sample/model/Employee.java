package com.terralogic.sample.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;
    
    @Getter @Setter
    private String firstName;
    
    @Getter @Setter
    private String lastName;
    
    @Getter @Setter
    private String email;
    
    @Getter @Setter
    private String mobile;
    
    @Getter @Setter
    private Date hireDate;
}
