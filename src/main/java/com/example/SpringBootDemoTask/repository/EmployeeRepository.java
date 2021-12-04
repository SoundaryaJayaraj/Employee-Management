package com.example.SpringBootDemoTask.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringBootDemoTask.model.Employee;

public interface EmployeeRepository  extends JpaRepository<Employee, Integer>{

}