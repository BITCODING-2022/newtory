package com.newtory.calendar;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newtory.calendar.resource.domain.MonthlyTodo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CalendarController {

	private final CalendarService calendarService;

	/**
	 * body : {
	 *     "year" : "2022",
	 *     "month" : "1"
	 * }
	 */
	@PostMapping("/calendar")
	public List<MonthlyTodo> calendar(@RequestBody CalendarPage calendarPage) {
		List<MonthlyTodo> monthlyTodos = calendarService.getMonthlyTodos(calendarPage);
		log.info(monthlyTodos.toString());
		return monthlyTodos;
	}

}
