package com.example.SpringBootDemoTask.dto;

import java.time.LocalDate;

public class TsReqDto {
	
	private int pageNo;
	private int pageSize;
	private String sortField;
	private String sortDir;
	private LocalDate date;
	
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getSortDir() {
		return sortDir;
	}
	public void setSortDir(String sortDir) {
		this.sortDir = sortDir;
	}

}
