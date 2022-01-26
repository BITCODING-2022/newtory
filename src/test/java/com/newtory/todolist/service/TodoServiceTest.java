package com.newtory.todolist.service;

import com.newtory.member.domain.Member;
import com.newtory.todolist.domain.DailyTodo;
import com.newtory.todolist.domain.FinishStatus;
import com.newtory.todolist.domain.MonthlyTodo;
import com.newtory.todolist.domain.Todo;
import com.newtory.todolist.repository.TodoRepository;
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
        DailyTodo dailyTodo = createDailyTodo(member);
        // when
        Long saveId = todoService.addTodo(dailyTodo);

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
        MonthlyTodo monthlyTodo = createMonthlyTodo(member);

        // when
        Long saveId = todoService.addTodo(monthlyTodo);

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
        DailyTodo dailyTodo = createDailyTodo(member);
        MonthlyTodo monthlyTodo = createMonthlyTodo(member);

        // when
        todoService.addTodo(dailyTodo);
        todoService.addTodo(monthlyTodo);

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
        DailyTodo dailyTodo = createDailyTodo(member);
        MonthlyTodo monthlyTodo = createMonthlyTodo(member);
        // when
        todoService.addTodo(dailyTodo);
        todoService.addTodo(monthlyTodo);

        // then
        List<MonthlyTodo> monthlyTodos = todoService.findMonthlyTodos();
        MonthlyTodo getTodo = monthlyTodos.get(0);

        Assertions.assertThat(getTodo.getMember()).isEqualTo(member);
        Assertions.assertThat(getTodo.getTitle()).isEqualTo("title2");
        Assertions.assertThat(getTodo.getDescription()).isEqualTo("description2");
    }







    private Member createMember() {
        Member member = new Member();
        em.persist(member);
        return member;
    }

    private DailyTodo createDailyTodo(Member member) {
        DailyTodo dailyTodo = new DailyTodo(member, "title1", "description1", FinishStatus.ON_GOING, LocalDateTime.now());
        em.persist(dailyTodo);
        return dailyTodo;
    }

    private MonthlyTodo createMonthlyTodo(Member member) {
        MonthlyTodo monthlyTodo = new MonthlyTodo(member, "title2", "description2", FinishStatus.ON_GOING, LocalDateTime.now(), LocalDateTime.now());
        em.persist(monthlyTodo);
        return monthlyTodo;
    }


}