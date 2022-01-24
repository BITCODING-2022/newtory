package com.newtory.calendar;

import java.util.List;

import org.springframework.stereotype.Service;

import com.newtory.calendar.resource.DBRepository;
import com.newtory.calendar.resource.domain.MonthlyTodo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalendarService {

	private final DBRepository dbRepository;

	public List<MonthlyTodo> getMonthlyTodos(CalendarPage calendarPage) {
		int year = calendarPage.getYear();
		int month = calendarPage.getMonth();
		log.info("Calendar of year: {}, month: {}", year, month);

		return dbRepository.findMonthlyTodos(year, month);
	}
}
