package com.example.SpringBootDemoTask.dto;

import java.util.Date;

import com.example.SpringBootDemoTask.model.Employee;

public class LeaveDto {

	private Integer lid;
	private String leaveType;
	private int duration;
	private Date fromDate;
	private Date toDate;
	private boolean halfday;
	private String reason;

	private Employee empid;

	public String getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public boolean getHalfday() {
		return halfday;
	}

	public void setHalfday(boolean halfday) {
		this.halfday = halfday;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Employee getEmpid() {
		return empid;
	}

	public void setEmpid(Employee empid) {
		this.empid = empid;
	}

	public Integer getLid() {
		return lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
	}

}
