package com.newtory.calendar;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	public String calendar(@RequestBody CalendarPage calendarPage) {
		calendarService.getMonthlyTodos(calendarPage);
		return "hello";
	}

}
