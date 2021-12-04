package com.example.SpringBootDemoTask.dto;

import java.util.List;

import com.example.SpringBootDemoTask.model.Employee;


public class ResponseDto {
	private int currentPage;
	private int totalPages;
	private long totalRecords;
	private List<Employee> employee;
	private String sortField; 
	private  String sortDir;
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public long getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}
	public List<Employee> getEmployee() {
		return employee;
	}
	public void setEmployees(List<Employee> employee) {
		this.employee = employee;
	}
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public String getSortDir() {
		return sortDir;
	}
	public void setSortDir(String sortDir) {
		this.sortDir = sortDir;
	}
	
}
