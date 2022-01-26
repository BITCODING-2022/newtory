package com.newtory.todolist.service;

import com.newtory.member.domain.Member;
import com.newtory.todolist.domain.DailyTodo;
import com.newtory.todolist.domain.FinishStatus;
import com.newtory.todolist.domain.MonthlyTodo;
import com.newtory.todolist.domain.Todo;
import com.newtory.todolist.repository.TodoRepository;
import com.newtory.todolist.web.dto.daily.DailyTodoSaveDto;
import com.newtory.todolist.web.dto.monthly.MonthlyTodoSaveDto;
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

import static org.junit.jupiter.api.Assertions.*;

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
        DailyTodoSaveDto dailyDto = createDailyTodoDto();
        // when
        Long saveId = todoService.addDailyTodo(member, dailyDto);

        // then
        Todo getTodo = todoRepository.findOne(saveId);
        Assertions.assertThat(getTodo.getMember()).isEqualTo(member);
        Assertions.assertThat(getTodo.getTitle()).isEqualTo("title1");
        Assertions.assertThat(getTodo.getDescription()).isEqualTo("description1");
    }

    @Test
//    @Rollback(value = false)
    public void MonthlyTodo_추가() throws Exception {
        // given
        Member member = createMember();
        MonthlyTodoSaveDto monthlyDto = createMonthlyTodo();

        // when
        Long saveId = todoService.addMonthlyTodo(member, monthlyDto);

        // then
        Todo getTodo = todoRepository.findOne(saveId);
        Assertions.assertThat(getTodo.getMember()).isEqualTo(member);
        Assertions.assertThat(getTodo.getTitle()).isEqualTo("title2");
        Assertions.assertThat(getTodo.getDescription()).isEqualTo("description2");
    }

    @Test
//    @Rollback(value = false)
    public void Todo_전체조회() throws Exception {
        // given
        Member member = createMember();
        DailyTodoSaveDto dailyDto = createDailyTodoDto();
        MonthlyTodoSaveDto monthlyDto = createMonthlyTodo();

        // when
        todoService.addDailyTodo(member, dailyDto);
        todoService.addMonthlyTodo(member, monthlyDto);

        // then
        List<Todo> todos = todoService.findTodos();
        Todo getTodo1 = todos.get(0);
        Todo getTodo2 = todos.get(1);

        Assertions.assertThat(getTodo1.getMember()).isEqualTo(member);
        Assertions.assertThat(getTodo1.getTitle()).isEqualTo("title1");
        Assertions.assertThat(getTodo1.getDescription()).isEqualTo("description1");

        Assertions.assertThat(getTodo2.getMember()).isEqualTo(member);
        Assertions.assertThat(getTodo2.getTitle()).isEqualTo("title2");
        Assertions.assertThat(getTodo2.getDescription()).isEqualTo("description2");
    }

    @Test
//    @Rollback(value = false)
    public void Monthly_고르기() throws Exception {
        // given
        Member member = createMember();
        DailyTodoSaveDto dailyDto = createDailyTodoDto();
        MonthlyTodoSaveDto monthlyDto = createMonthlyTodo();
        // when
        todoService.addDailyTodo(member, dailyDto);
        todoService.addMonthlyTodo(member, monthlyDto);

        // then
        List<MonthlyTodo> monthlyTodos = todoService.findMonthlyTodos();
        MonthlyTodo getTodo = monthlyTodos.get(0);

        Assertions.assertThat(getTodo.getMember()).isEqualTo(member);
        Assertions.assertThat(getTodo.getTitle()).isEqualTo("title2");
        Assertions.assertThat(getTodo.getDescription()).isEqualTo("description2");
    }

    @Test
//    @Rollback(value = false)
    public void TODO_삭제() throws Exception {
        // given
        Member member = createMember();
        DailyTodoSaveDto dailyDto = createDailyTodoDto();
        MonthlyTodoSaveDto monthlyDto = createMonthlyTodo();

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

    private DailyTodoSaveDto createDailyTodoDto() {
        DailyTodoSaveDto dailyDto = new DailyTodoSaveDto(
                "title1",
                "description1",
                FinishStatus.ON_GOING,
                LocalDateTime.now());
        return dailyDto;
    }

    private MonthlyTodoSaveDto createMonthlyTodo() {
        MonthlyTodoSaveDto monthlyDto = new MonthlyTodoSaveDto(
                "title2",
                "description2",
                FinishStatus.ON_GOING,
                LocalDateTime.now(),
                LocalDateTime.now());
        return monthlyDto;
    }


}