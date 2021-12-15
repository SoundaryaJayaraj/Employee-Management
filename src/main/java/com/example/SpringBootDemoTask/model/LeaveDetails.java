package com.example.SpringBootDemoTask.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "leave_records")
public class LeaveDetails {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer lid;
	private float duration;
	private Date fromDate;
	private Date toDate;
	private boolean halfday;
	private String reason;

	@ManyToOne
	@JoinColumn(name = "id")
	private Employee empId;
	
	@ManyToOne
	@JoinColumn(name="ltId")
	private LeaveType ltId;
	
	
	public LeaveType getLtId() {
		return ltId;
	}

	public void setLtId(LeaveType ltId) {
		this.ltId = ltId;
	}

	public Integer getLid() {
		return lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
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

	public boolean isHalfday() {
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

	public Employee getEmpId() {
		return empId;
	}

	public void setEmpId(Employee empId) {
		this.empId = empId;
	}
	
	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

}
