package com.newtory.calendar;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.newtory.calendar.resource.DBRepository;
import com.newtory.calendar.resource.DataBase;

class CalendarTest {

	@Test
	public void 월간_투두_조회() {
	    //given
		DataBase dataBase = new DataBase();
		dataBase.testMonthlyTodoJan();
		dataBase.testMonthlyTodoFeb();

		//when
		DBRepository dbRepository = new DBRepository(dataBase);

		//then
		assertThat(dbRepository.findMonthlyTodos(2022, 1)).size().isEqualTo(2);
		assertThat(dbRepository.findMonthlyTodos(2022, 2)).size().isEqualTo(3);
	}

}