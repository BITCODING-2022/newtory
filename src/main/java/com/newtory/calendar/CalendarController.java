package com.newtory.calendar;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newtory.todolist.service.TodoService;
import com.newtory.todolist.web.dto.monthly.MonthlyTodoResponseDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CalendarController {

	private final TodoService todoService;

	/**
	 * body : {
	 *     "year" : "2022",
	 *     "month" : "1"
	 * }
	 */
	@PostMapping("/calendar")
	public List<MonthlyTodoResponseDto> calendar(@RequestBody CalendarPage calendarPage) {
		log.info("Calendar new Request : {}", calendarPage);
		List<MonthlyTodoResponseDto> monthlyTodos = todoService.findMonthlyTodos();
		log.info(monthlyTodos.toString());
		return monthlyTodos;
	}

	@GetMapping("/calendar")
	public List<MonthlyTodoResponseDto> calendar() {
		List<MonthlyTodoResponseDto> monthlyTodos = todoService.findMonthlyTodos();
		log.info("Get {} monthlyTodos", monthlyTodos.size());
		return monthlyTodos;
	}

}
