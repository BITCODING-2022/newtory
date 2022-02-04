package com.newtory.todolist.repository;

import com.newtory.todolist.domain.DailyTodo;
import com.newtory.todolist.domain.MonthlyTodo;
import com.newtory.todolist.domain.Todo;
import com.newtory.todolist.web.dto.daily.DailyTodoResponseDto;
import com.newtory.todolist.web.dto.monthly.MonthlyTodoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class TodoRepository {

    private final EntityManager em;

    public void save(Todo todo) {
        em.persist(todo);
    }

    public Todo findOne(Long id) {
        return em.find(Todo.class, id);
    }

    public List<Todo> findAll() {
        return em.createQuery("select t from Todo t", Todo.class)
                .getResultList();
    }

    public List<DailyTodoResponseDto> findAllDailyTodo() {
        List<DailyTodo> select_d_from_dailyTodo_d = em.createQuery("select d from DailyTodo d", DailyTodo.class)
                .getResultList();
        List<DailyTodoResponseDto> dailyTodos = new ArrayList<>();
        for (DailyTodo dailyTodo : select_d_from_dailyTodo_d) {
            DailyTodoResponseDto dailyTodoResponseDto = new DailyTodoResponseDto(dailyTodo);
            dailyTodos.add(dailyTodoResponseDto);
        }
        return dailyTodos;
    }

    public List<MonthlyTodoResponseDto> findAllMonthlyTodo() {
        List<MonthlyTodo> select_m_from_monthlyTodo_m = em.createQuery("select m from MonthlyTodo m", MonthlyTodo.class)
                .getResultList();
        List<MonthlyTodoResponseDto> monthlyTodos = new ArrayList<>();
        for (MonthlyTodo monthlyTodo : select_m_from_monthlyTodo_m) {
            MonthlyTodoResponseDto monthlyTodoResponseDto = new MonthlyTodoResponseDto(monthlyTodo);
            monthlyTodos.add(monthlyTodoResponseDto);
        }
        return monthlyTodos;
    }

    public void delete(Long id) {
        em.remove(findOne(id));
    }

    public void deleteAll() {
        em.createQuery("delete from Todo");
    }
}
