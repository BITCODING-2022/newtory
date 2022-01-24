package com.newtory.calendar.resource.domain;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class MonthlyTodo {

	private Long memberId;
	private LocalDate startDate;
	private LocalDate endDate;
	private String description;

	public MonthlyTodo(Long memberId, LocalDate startDate, LocalDate endDate, String description) {
		this.memberId = memberId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
	}
}
