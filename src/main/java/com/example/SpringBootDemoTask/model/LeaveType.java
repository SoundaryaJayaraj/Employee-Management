package com.example.SpringBootDemoTask.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "leave_type")
public class LeaveType {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ltId;
	private String leaveTypes;
	
	
	public Integer getLtId() {
		return ltId;
	}
	public void setLtId(Integer ltId) {
		this.ltId = ltId;
	}
	public String getLeaveTypes() {
		return leaveTypes;
	}
	public void setLeaveTypes(String leaveTypes) {
		this.leaveTypes = leaveTypes;
	}
	
}
