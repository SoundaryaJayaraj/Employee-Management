package com.example.SpringBootDemoTask.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringBootDemoTask.model.Employee;

public interface EmployeeRepository  extends JpaRepository<Employee, Integer>{

	Optional<Employee> findById(Integer id);

}