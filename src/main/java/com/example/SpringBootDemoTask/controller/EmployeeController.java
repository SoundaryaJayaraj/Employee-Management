package com.example.SpringBootDemoTask.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootDemoTask.dto.EmployeerequestDto;
import com.example.SpringBootDemoTask.dto.ResponseDto;
import com.example.SpringBootDemoTask.exception.ResourceNotFoundException;
import com.example.SpringBootDemoTask.model.AuthRequest;
import com.example.SpringBootDemoTask.model.Employee;
import com.example.SpringBootDemoTask.model.JwtUtil;
import com.example.SpringBootDemoTask.repository.UserRepository;
import com.example.SpringBootDemoTask.repository.EmployeeRepository;
import com.example.SpringBootDemoTask.service.CustomUserDetailsService;
import com.example.SpringBootDemoTask.service.EmployeeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	EmployeeRepository employeerepository;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	CustomUserDetailsService userDetailsService;

	@Autowired
	UserRepository authrepository;

	@Autowired
	EmployeeService employeeService;

	@PostMapping("/employee")
	@ApiOperation( value = "Fill necessary details to create an employee")
	public Employee createEmployee(@RequestBody EmployeerequestDto dto) {

		Employee employee = new Employee();
		employee.setName(dto.getName());
		employee.setDepartment(dto.getDepartment());
		employee.setSalary(dto.getSalary());
		employee.setCheckin(dto.getCheckin());
		employee.setStatus(dto.getStatus());
		return employeerepository.save(employee);
	}

	@GetMapping("/employees")
	@ApiOperation( value = "Get all Employee details")
	public List<Employee> getAllEmployee() {
		return employeerepository.findAll();
	}

	@GetMapping("/employee/{id}")
	@ApiOperation( value = "Enter Employee Id to view details of Employee")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(name = "id") Integer employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeerepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id : " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	@PutMapping("/employee/{id}")
	@ApiOperation( value = "Enter Employee Id to make changes for corresponding employee")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Integer employeeId,
			@RequestBody EmployeerequestDto dto) throws ResourceNotFoundException {
		Employee employee = employeerepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		employee.setName(dto.getName());
		employee.setDepartment(dto.getDepartment());
		employee.setSalary(dto.getSalary());
		employee.setCheckin(dto.getCheckin());
		employee.setStatus(dto.getStatus());
		final Employee updatedEmployee = employeerepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/employee/{id}")
	@ApiOperation( value = "Enter Employee Id to delete the Employee")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Integer employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeerepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		employeerepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@GetMapping("/page/{pageNo}")
	public ResponseDto findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("pageSize") int pageSize,
			@RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir) {
		ResponseDto responsedto = new ResponseDto();

		Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Employee> listEmployees = page.getContent();

		responsedto.setEmployees(listEmployees);
		responsedto.setCurrentPage(pageNo);
		responsedto.setTotalPages(page.getTotalPages());
		responsedto.setTotalRecords(page.getTotalElements());
		responsedto.setSortField(sortField);
		responsedto.setSortDir(sortDir);
		return responsedto;
	}

	@PostMapping("/authenticate")
	@ApiOperation( value = "Login by username and password")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		} catch (Exception ex) {
			throw new Exception("inavalid username/password");
		}
		return jwtUtil.generateToken(authRequest.getUserName());
	}
}
