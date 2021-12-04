package com.example.SpringBootDemoTask.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.SpringBootDemoTask.model.Employee;

public interface EmployeeService {

	Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDir);

	List<Employee> listAll();
}
