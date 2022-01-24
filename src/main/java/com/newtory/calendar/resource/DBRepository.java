package com.newtory.calendar.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.newtory.calendar.resource.domain.MonthlyTodo;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DBRepository {

	private final DataBase dataBase;

	public List<MonthlyTodo> findMonthlyTodos(int year, int month) {
		return
			dataBase.getMonthlyTodoList().stream()
			.filter(todos -> todos.getStartDate().getYear() == year
				&& todos.getStartDate().getMonthValue() == month)
			.collect(Collectors.toList());
	}

	public List<MonthlyTodo> findAllMonthlyTodos() {
		return dataBase.getMonthlyTodoList();
	}


}
