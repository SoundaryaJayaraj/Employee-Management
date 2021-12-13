package com.example.SpringBootDemoTask.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringBootDemoTask.dto.LeaveDto;
import com.example.SpringBootDemoTask.exception.ResourceNotFoundException;
import com.example.SpringBootDemoTask.model.Employee;
import com.example.SpringBootDemoTask.model.LeaveDetails;
import com.example.SpringBootDemoTask.repository.EmployeeRepository;
import com.example.SpringBootDemoTask.repository.LeaveDetailsRepository;

@RestController
@RequestMapping("/api/v1")
public class LeaveDetailsController {

	@Autowired
	LeaveDetailsRepository leaverepo;

	@Autowired
	EmployeeRepository employeerepository;

	@PostMapping("/applyForLeave/{empId}")
	public String applyForLeave(@PathVariable(value = "empId") Integer empId, @RequestBody LeaveDto leavedto) {
		try {
			LeaveDetails leave = new LeaveDetails();
			leave.setLeaveType(leavedto.getLeaveType());
			leave.setDuration(leavedto.getDuration());
			leave.setFromDate(leavedto.getFromDate());
			leave.setToDate(leavedto.getToDate());
			leave.setHalfday(leavedto.getHalfday());
			leave.setReason(leavedto.getReason());
			// finding the employee
			Employee employee = employeerepository.findById(empId).get();
			leave.setEmpId(employee);
			leaverepo.save(leave);
			return "Applied for Leave!!";
		} catch (Exception e) {
			return "Failed to Apply Leave!!";
		}
	}

	@GetMapping("/leaveRecords")
	public Iterable<LeaveDetails> getLeaveRecords() {
		return leaverepo.findAll();
	}

	@PutMapping("/updateLeave/{lid}")
	public ResponseEntity<LeaveDetails> updateLeaveRecord(@PathVariable(value = "lid") Integer leaveLid,
			@RequestBody LeaveDto leavedto) {
		LeaveDetails leave = leaverepo.findById(leaveLid)
				.orElseThrow(() -> new ResourceNotFoundException("No records to update"));
		leave.setLeaveType(leavedto.getLeaveType());
		leave.setDuration(leavedto.getDuration());
		leave.setFromDate(leavedto.getFromDate());
		leave.setToDate(leavedto.getToDate());
		leave.setHalfday(leavedto.getHalfday());
		leave.setReason(leavedto.getReason());
		final LeaveDetails updatedrecords = leaverepo.save(leave);
		return ResponseEntity.ok(updatedrecords);
	}

	@GetMapping("/leaveRecords/{empId}")
	public List<LeaveDetails> getLeaveRecords(@PathVariable(value = "empId") Integer empId) {
		try {
			// finding the employee
			Employee employee = employeerepository.findById(empId).get();
			List<LeaveDetails> leaverecord = leaverepo.findAllByEmpId(employee);
			return leaverecord;
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}
}
