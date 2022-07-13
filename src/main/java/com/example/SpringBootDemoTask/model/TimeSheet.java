package com.example.SpringBootDemoTask.model;


import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "time_sheet")
public class TimeSheet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tsId;
	private LocalDate date;
	private String description;
	private String project;
	private String task;
	private int duration;
	
	@ManyToOne
	@JoinColumn(name = "id")
	private Employee empId;
		
	
	public Employee getEmpId() {
		return empId;
	}
	public void setEmpId(Employee empId) {
		this.empId = empId;
	}
		public Integer getTsId() {
		return tsId;
	}
	public void setTsId(Integer tsId) {
		this.tsId = tsId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
//	public Duration getDuration() {
//		return duration;
//	}
//	public void setDuration(Duration duration) {
//		this.duration = duration;
//	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	
	
}
