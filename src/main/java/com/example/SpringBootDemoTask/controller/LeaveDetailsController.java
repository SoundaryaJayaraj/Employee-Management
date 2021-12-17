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
import com.example.SpringBootDemoTask.model.LeaveType;
import com.example.SpringBootDemoTask.repository.EmployeeRepository;
import com.example.SpringBootDemoTask.repository.LeaveDetailsRepository;
import com.example.SpringBootDemoTask.repository.LeaveTypeRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
public class LeaveDetailsController {

	@Autowired
	private LeaveDetailsRepository leaverepo;

	@Autowired
	private EmployeeRepository employeerepository;

	@Autowired
	private LeaveTypeRepository levtyprepo;

	@PostMapping("/applyForLeave/{empId}")
	@ApiOperation( value = "Apply for Leave by giving all details")
	public String applyForLeave(@PathVariable(value = "empId") Integer empId, @RequestBody LeaveDto leavedto) {

		try {
			// finding the leave type
			LeaveType levtype = levtyprepo.findByLtId(leavedto.getLtId()).get();

			LeaveDetails leave = new LeaveDetails();
			leave.setLtId(levtype);
			leave.setDuration(duration(leavedto));
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
	@ApiOperation( value = "Update Leave records")
	public ResponseEntity<LeaveDetails> updateLeaveRecord(@PathVariable(value = "lid") Integer leaveLid,
			@RequestBody LeaveDto leavedto) {
		LeaveDetails leave = leaverepo.findById(leaveLid)
				.orElseThrow(() -> new ResourceNotFoundException("No records to update"));

		if (leavedto.getLtId() != null) {
			LeaveType levtype = levtyprepo.findByLtId(leavedto.getLtId()).get();
			leave.setLtId(levtype);
		}
		if (leavedto.getFromDate() != null && leavedto.getToDate() != null) {
			leave.setFromDate(leavedto.getFromDate());
			leave.setToDate(leavedto.getToDate());
			leave.setDuration(duration(leavedto));
		}
		if (leavedto.getHalfday()) {
			leave.setHalfday(leavedto.getHalfday());
		}
		if (leavedto.getReason() != null) {
			leave.setReason(leavedto.getReason());
		}
		final LeaveDetails updatedrecords = leaverepo.save(leave);
		return ResponseEntity.ok(updatedrecords);
	}

	@GetMapping("/leaveRecords/{empId}")
	@ApiOperation( value = "Enter Employee Id to view all leave records corresponding to the employee")
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

	private float duration(LeaveDto leavedto) {
		long difference = leavedto.getToDate().getTime() - leavedto.getFromDate().getTime();
		float daysBetween = (difference / (1000 * 60 * 60 * 24));
		return daysBetween;
	}
}
