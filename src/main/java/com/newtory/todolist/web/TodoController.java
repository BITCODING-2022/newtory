package com.newtory.todolist.web;

import com.newtory.member.service.MemberService;
import com.newtory.todolist.domain.DailyTodo;
import com.newtory.todolist.domain.MonthlyTodo;
import com.newtory.todolist.service.TodoService;
import com.newtory.todolist.web.dto.daily.DailyTodoSaveDto;
import com.newtory.todolist.web.dto.daily.DailyTodoUpdateDto;
import com.newtory.todolist.web.dto.monthly.MonthlyTodoSaveDto;
import com.newtory.todolist.web.dto.monthly.MonthlyTodoUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TodoController {

    private final TodoService todoService;
    private final MemberService memberService;

    // == 일정 추가하기 (C) ==

    @PostMapping("/{memberId}/save/daily")
    public Long saveDailyTodo(@PathVariable("memberId") Long memberId,
                              @RequestBody DailyTodoSaveDto dto) {
        return todoService.addDailyTodo(memberService.findOne(memberId), dto);
    }

    @PostMapping("/{memberId}/save/monthly")
    public Long saveMonthlyTodo(@PathVariable("memberId") Long memberId,
                                @RequestBody MonthlyTodoSaveDto dto) {

        return todoService.addMonthlyTodo(memberService.findOne(memberId), dto);
    }

    // == 일정 조회하기 (R) ==

    @PostMapping("/dailyTodos")
    public List<DailyTodo> dailyTodoList(Model model) {
        return todoService.findDailyTodos();
    }

    @PostMapping("/monthlyTodos")
    public List<MonthlyTodo> monthlyTodoList(Model model) {
        return todoService.findMonthlyTodos();
    }

    // == 일정 수정하기 (U) ==

    @PostMapping("/{memberId}/{todoId}/daily/update")
    public Long updateDailyTodo(@PathVariable("memberId") Long memberId,
                                @PathVariable("todoId") Long todoId,
                                @RequestBody DailyTodoUpdateDto dto) {
        return todoService.updateDailyTodo(todoId, dto);
    }

    @PostMapping("/{memberId}/{todoId}/monthly/update")
    public Long updateMonthlyTodo(@PathVariable("memberId") Long memberId,
                                  @PathVariable("todoId") Long todoId,
                                  @RequestBody MonthlyTodoUpdateDto dto) {
        return todoService.updateMonthlyTodo(todoId, dto);
    }

    // == 일정 삭제하기 (D) ==

    @PostMapping("/{memberId}/{todoId}")
    public void deleteTodo(@PathVariable("memberId") Long memberId,
                           @PathVariable("todoId") Long todoId) {
        todoService.deleteTodo(todoId);
    }


}
