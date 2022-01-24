package com.newtory.calendar.resource.domain;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class MonthlyTodo {

	private Long memberId;
	private Long monthlyTodoId;
	private LocalDate startDate;
	private LocalDate endDate;
	private String description;

	public MonthlyTodo(Long memberId, Long monthlyTodoId, LocalDate startDate, LocalDate endDate,
		String description) {
		this.memberId = memberId;
		this.monthlyTodoId = monthlyTodoId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
	}

	@Override
	public String toString() {
		return "\n{memberId : " + memberId +
			", startDate : " + startDate +
			", endDate : " + endDate +
			", description : " + description +"}";
	}
}
