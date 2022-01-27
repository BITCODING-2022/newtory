package com.newtory.todolist.service;

import com.newtory.member.domain.Member;
import com.newtory.todolist.domain.DailyTodo;
import com.newtory.todolist.domain.MonthlyTodo;
import com.newtory.todolist.domain.Todo;
import com.newtory.todolist.repository.TodoRepository;
import com.newtory.todolist.web.dto.daily.DailyTodoSaveDto;
import com.newtory.todolist.web.dto.daily.DailyTodoUpdateDto;
import com.newtory.todolist.web.dto.monthly.MonthlyTodoSaveDto;
import com.newtory.todolist.web.dto.monthly.MonthlyTodoUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    // == 일정 추가하기 (C) ==

    @Transactional
    public Long addDailyTodo(Member member, DailyTodoSaveDto dto) {
        DailyTodo dailyTodo = new DailyTodo(
                member,
                dto.getTitle(),
                dto.getDescription(),
                dto.getStatus(),
                dto.getStartDate());
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
                dto.getEndDate());
        todoRepository.save(monthlyTodo);
        return monthlyTodo.getId();
    }

    // == 일정 조회하기 (R) ==

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

    // == 일정 수정하기 (U) ==
    @Transactional
    public Long updateDailyTodo(Long todoId, DailyTodoUpdateDto dto) {
        DailyTodo findOne = (DailyTodo) todoRepository.findOne(todoId);
        findOne.update(dto.getTitle(),
                dto.getDescription(),
                dto.getStartDate());
        return todoId;
    }

    @Transactional
    public Long updateMonthlyTodo(Long todoId, MonthlyTodoUpdateDto dto) {
        MonthlyTodo findOne = (MonthlyTodo) todoRepository.findOne(todoId);
        findOne.update(dto.getTitle(),
                dto.getDescription(),
                dto.getStartDate(),
                dto.getEndDate());
        return todoId;
    }

    // == 일정 삭제하기 (D) ==

    @Transactional
    public void deleteTodo(Long id) {
        todoRepository.delete(id);
    }
}
