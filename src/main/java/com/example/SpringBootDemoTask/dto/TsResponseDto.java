package com.example.SpringBootDemoTask.dto;

import java.time.LocalDate;

public class TsResponseDto {

	private LocalDate date;
	private int duration;

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
