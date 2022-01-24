package com.newtory.calendar.resource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.newtory.calendar.resource.domain.Member;
import com.newtory.calendar.resource.domain.MonthlyTodo;

import lombok.Getter;

/**
 * Test를 위한 임의의 DB
 * @memberMap {
 * 	{1L, memberA},
 * 	{2L, memberB}
 * 	}
 *
 * @monthlyTodoList [
 *     monthlyTodoA,
 *     monthlyTodoB
 * ]
 */
@Component
@Getter
public class DataBase {

	private Map<Long, Member> memberMap = new ConcurrentHashMap<>();
	private List<MonthlyTodo> monthlyTodoList = new ArrayList<>();

	@PostConstruct
	public void testMember() {
		Member memberA = new Member(1L);
		Member memberB = new Member(2L);

		memberMap.put(memberA.getId(), memberA);
		memberMap.put(memberB.getId(), memberB);
	}

	@PostConstruct
	public void testMonthlyTodoJan() {
		LocalDate startDate = LocalDate.of(2022, 1, 21);
		LocalDate endDate = LocalDate.of(2022, 1, 24);

		MonthlyTodo monthlyTodoA = new MonthlyTodo(1L, startDate, endDate, "부산 여행");
		MonthlyTodo monthlyTodoB = new MonthlyTodo(2L, startDate, endDate, "서울 여행");

		monthlyTodoList.add(monthlyTodoA);
		monthlyTodoList.add(monthlyTodoB);
	}

	@PostConstruct
	public void testMonthlyTodoFeb() {
		LocalDate startDate = LocalDate.of(2022, 2, 11);
		LocalDate endDate = LocalDate.of(2022, 2, 19);

		MonthlyTodo monthlyTodoA = new MonthlyTodo(1L, startDate, endDate, "경주 여행");
		MonthlyTodo monthlyTodoB = new MonthlyTodo(2L, startDate, endDate, "인천 여행");
		MonthlyTodo monthlyTodoC = new MonthlyTodo(2L, startDate, endDate, "대전 여행");

		monthlyTodoList.add(monthlyTodoA);
		monthlyTodoList.add(monthlyTodoB);
		monthlyTodoList.add(monthlyTodoC);
	}
}
