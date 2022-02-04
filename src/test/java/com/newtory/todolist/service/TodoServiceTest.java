package com.newtory.todolist.service;

import com.newtory.member.domain.Member;
import com.newtory.todolist.domain.FinishStatus;
import com.newtory.todolist.domain.MonthlyTodo;
import com.newtory.todolist.domain.Todo;
import com.newtory.todolist.repository.TodoRepository;
import com.newtory.todolist.web.dto.daily.DailyTodoSaveDto;
import com.newtory.todolist.web.dto.daily.DailyTodoUpdateDto;
import com.newtory.todolist.web.dto.monthly.MonthlyTodoResponseDto;
import com.newtory.todolist.web.dto.monthly.MonthlyTodoSaveDto;
import com.newtory.todolist.web.dto.monthly.MonthlyTodoUpdateDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class TodoServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    TodoService todoService;

    @Autowired
    TodoRepository todoRepository;

    @Test
//    @Rollback(value = false)
    public void DailyTodo_추가() throws Exception {
        // given
        Member member = createMember();
        DailyTodoSaveDto dailyDto = createDailySaveTodoDto();
        // when
        Long saveId = todoService.addDailyTodo(member, dailyDto);

        // then
        Todo getTodo = todoRepository.findOne(saveId);
        Assertions.assertThat(getTodo.getMember()).isEqualTo(member);
        Assertions.assertThat(getTodo.getTitle()).isEqualTo("daily_title");
        Assertions.assertThat(getTodo.getDescription()).isEqualTo("daily_description");
    }

    @Test
//    @Rollback(value = false)
    public void MonthlyTodo_추가() throws Exception {
        // given
        Member member = createMember();
        MonthlyTodoSaveDto monthlyDto = createMonthlySaveTodoDto();

        // when
        Long saveId = todoService.addMonthlyTodo(member, monthlyDto);

        // then
        Todo getTodo = todoRepository.findOne(saveId);
        Assertions.assertThat(getTodo.getMember()).isEqualTo(member);
        Assertions.assertThat(getTodo.getTitle()).isEqualTo("monthly_title");
        Assertions.assertThat(getTodo.getDescription()).isEqualTo("monthly_description");
    }

    @Test
//    @Rollback(value = false)
    public void Todo_전체조회() throws Exception {
        // given
        Member member = createMember();
        DailyTodoSaveDto dailyDto = createDailySaveTodoDto();
        MonthlyTodoSaveDto monthlyDto = createMonthlySaveTodoDto();

        // when
        todoService.addDailyTodo(member, dailyDto);
        todoService.addMonthlyTodo(member, monthlyDto);

        // then
        List<Todo> todos = todoService.findTodos();
        Todo getTodo1 = todos.get(0);
        Todo getTodo2 = todos.get(1);

        Assertions.assertThat(getTodo1.getMember()).isEqualTo(member);
        Assertions.assertThat(getTodo1.getTitle()).isEqualTo("daily_title");
        Assertions.assertThat(getTodo1.getDescription()).isEqualTo("daily_description");

        Assertions.assertThat(getTodo2.getMember()).isEqualTo(member);
        Assertions.assertThat(getTodo2.getTitle()).isEqualTo("monthly_title");
        Assertions.assertThat(getTodo2.getDescription()).isEqualTo("monthly_description");
    }

    @Test
//    @Rollback(value = false)
    public void Monthly_고르기() throws Exception {
        // given
        Member member = createMember();
        DailyTodoSaveDto dailyDto = createDailySaveTodoDto();
        MonthlyTodoSaveDto monthlyDto = createMonthlySaveTodoDto();
        // when
        todoService.addDailyTodo(member, dailyDto);
        todoService.addMonthlyTodo(member, monthlyDto);

        // then
        List<MonthlyTodoResponseDto> monthlyTodos = todoService.findMonthlyTodos();
        MonthlyTodoResponseDto getTodo = monthlyTodos.get(0);

        Assertions.assertThat(getTodo.getTitle()).isEqualTo("monthly_title");
        Assertions.assertThat(getTodo.getDescription()).isEqualTo("monthly_description");
    }

    @Test
//    @Rollback(value = false)
    public void Daily_수정하기() throws Exception {
        // given
        Member member = createMember();
        DailyTodoSaveDto dailyDto = createDailySaveTodoDto();
        Long saveId = todoService.addDailyTodo(member, dailyDto);

        // when
        DailyTodoUpdateDto dto = createDailyUpdateTodoDto();
        todoService.updateDailyTodo(saveId, dto);

        // then
        Todo getTodo = todoRepository.findOne(saveId);
        Assertions.assertThat(getTodo.getTitle()).isEqualTo("update_daily_title");
        Assertions.assertThat(getTodo.getDescription()).isEqualTo("update_daily_description");
    }

    @Test
    public void Monthly_수정하기() throws Exception {
        // given
        Member member = createMember();
        MonthlyTodoSaveDto monthlyDto = createMonthlySaveTodoDto();
        Long saveId = todoService.addMonthlyTodo(member, monthlyDto);

        // when
        MonthlyTodoUpdateDto dto = createMonthlyUpdateTodoDto();
        todoService.updateMonthlyTodo(saveId, dto);

        // then
        Todo getTodo = todoRepository.findOne(saveId);
        Assertions.assertThat(getTodo.getTitle()).isEqualTo("update_monthly_title");
        Assertions.assertThat(getTodo.getDescription()).isEqualTo("update_monthly_description");
    }

    @Test
//    @Rollback(value = false)
    public void TODO_삭제() throws Exception {
        // given
        Member member = createMember();
        DailyTodoSaveDto dailyDto = createDailySaveTodoDto();
        MonthlyTodoSaveDto monthlyDto = createMonthlySaveTodoDto();

        Long saveId = todoService.addDailyTodo(member, dailyDto);
        todoService.addMonthlyTodo(member, monthlyDto);

        // when
        todoService.deleteTodo(saveId);

        // then
        List<Todo> todos = todoService.findTodos();
        Assertions.assertThat(todos.size()).isEqualTo(1);
    }







    private Member createMember() {
        Member member = new Member();
        em.persist(member);
        return member;
    }
    private DailyTodoSaveDto createDailySaveTodoDto() {
        DailyTodoSaveDto dailyDto = new DailyTodoSaveDto(
                "daily_title",
                "daily_description",
                FinishStatus.ON_GOING,
                LocalDateTime.now());
        return dailyDto;
    }

    private MonthlyTodoSaveDto createMonthlySaveTodoDto() {
        MonthlyTodoSaveDto monthlyDto = new MonthlyTodoSaveDto(
                "monthly_title",
                "monthly_description",
                FinishStatus.ON_GOING,
                LocalDateTime.now(),
                LocalDateTime.now());
        return monthlyDto;
    }

    private DailyTodoUpdateDto createDailyUpdateTodoDto() {
        DailyTodoUpdateDto dailyDto = new DailyTodoUpdateDto(
                "update_daily_title",
                "update_daily_description",
                LocalDateTime.now());
        return dailyDto;
    }

    private MonthlyTodoUpdateDto createMonthlyUpdateTodoDto() {
        MonthlyTodoUpdateDto monthlyDto = new MonthlyTodoUpdateDto(
                "update_monthly_title",
                "update_monthly_description",
                LocalDateTime.now(),
                LocalDateTime.now());
        return monthlyDto;
    }
}