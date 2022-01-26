package com.newtory.todolist.service;

import com.newtory.todolist.domain.DailyTodo;
import com.newtory.todolist.domain.MonthlyTodo;
import com.newtory.todolist.domain.Todo;
import com.newtory.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public Long addTodo(Todo todo) {
        todoRepository.save(todo);
        return todo.getId();
    }

    @Transactional
    public Todo findOne(Long id) {
        return todoRepository.findOne(id);
    }

    @Transactional
    public List<Todo> findTodos() {
        return todoRepository.findAll();
    }

    @Transactional
    public List<DailyTodo> findDailyTodos() {
        return todoRepository.findAllDailyTodo();
    }

    @Transactional
    public List<MonthlyTodo> findMonthlyTodos() {
        return todoRepository.findAllMonthlyTodo();
    }

    @Transactional
    public void deleteTodo(Long id) {
        todoRepository.delete(id);
    }
}
