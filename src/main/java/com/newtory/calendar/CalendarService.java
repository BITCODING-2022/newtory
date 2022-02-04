package com.newtory.calendar;

import java.util.List;

import org.springframework.stereotype.Service;

import com.newtory.todolist.service.TodoService;
import com.newtory.todolist.web.dto.monthly.MonthlyTodoResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalendarService {

	private final TodoService todoService;

	public List<MonthlyTodoResponseDto> getMonthlyTodos(CalendarPage calendarPage) {
		int year = calendarPage.getYear();
		int month = calendarPage.getMonth();
		log.info("Calendar of year: {}, month: {}", year, month);

		return todoService.findMonthlyTodos();
	}

	public List<MonthlyTodoResponseDto> getMonthlyTodos() {
		return todoService.findMonthlyTodos();
	}
}
