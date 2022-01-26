package com.newtory.todolist.repository;

import com.newtory.todolist.domain.DailyTodo;
import com.newtory.todolist.domain.MonthlyTodo;
import com.newtory.todolist.domain.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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

    public List<DailyTodo> findAllDailyTodo() {
        return em.createQuery("select d from DailyTodo d", DailyTodo.class)
                .getResultList();
    }

    public List<MonthlyTodo> findAllMonthlyTodo() {
        return em.createQuery("select m from MonthlyTodo m", MonthlyTodo.class)
                .getResultList();
    }
}
