package com.example.SpringBootDemoTask.dto;

import java.util.Date;

public class LeaveDto {

	private Integer lid;
	private float duration;
	private Date fromDate;
	private Date toDate;
	private boolean halfday;
	private String reason;

	private Integer empid;

	private Integer ltId;
	
	
	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
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

//	public Employee getEmpid() {
//		return empid;
//	}
//
//	public void setEmpid(Employee empid) {
//		this.empid = empid;
//	}

	public Integer getLid() {
		return lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
	}

	public Integer getLtId() {
		return ltId;
	}

	public void setLtId(Integer ltId) {
		this.ltId = ltId;
	}

	public Integer getEmpid() {
		return empid;
	}

	public void setEmpid(Integer empid) {
		this.empid = empid;
	}

	

}
