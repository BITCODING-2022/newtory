package com.newtory.todolist.service;

import com.newtory.member.domain.Member;
import com.newtory.todolist.domain.DailyTodo;
import com.newtory.todolist.domain.MonthlyTodo;
import com.newtory.todolist.domain.Todo;
import com.newtory.todolist.repository.TodoRepository;
import com.newtory.todolist.web.dto.daily.DailyTodoSaveDto;
import com.newtory.todolist.web.dto.monthly.MonthlyTodoSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public Long addDailyTodo(Member member, DailyTodoSaveDto dto) {
        DailyTodo dailyTodo = new DailyTodo(
                member,
                dto.getTitle(),
                dto.getDescription(),
                dto.getStatus(),
                dto.getStartDate()); // 이후에 이 로직을 Service 또는 Domain으로 옮길 필요 있음
        todoRepository.save(dailyTodo);
        return dailyTodo.getId();
    }

    @Transactional
    public Long addMonthlyTodo(Member member, MonthlyTodoSaveDto dto) {
        MonthlyTodo monthlyTodo = new MonthlyTodo(
                member,
                dto.getTitle(),
                dto.getDescription(),
                dto.getStatus(),
                dto.getStartDate(),
                dto.getEndDate()); // 이후에 이 로직을 Service 또는 Domain으로 옮길 필요 있음
        todoRepository.save(monthlyTodo);
        return monthlyTodo.getId();
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
