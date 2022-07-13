package com.example.SpringBootDemoTask.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringBootDemoTask.model.Employee;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Integer>{

	Optional<Employee> findById(Integer id);

}