package com.example.SpringBootDemoTask.dto;

import java.time.Duration;
import java.time.LocalDate;

public class TimeSheetDto {

	private Integer tsId;
	private LocalDate date;
	private String description;
	private String project;
	private String task;
	private int duration;
	
	private Integer empId;
	
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
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Integer getTsId() {
		return tsId;
	}
	public void setTsId(Integer tsId) {
		this.tsId = tsId;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	
}
