package com.newtory.todolist.web;

import com.newtory.member.service.MemberService;
import com.newtory.todolist.domain.DailyTodo;
import com.newtory.todolist.domain.MonthlyTodo;
import com.newtory.todolist.service.TodoService;
import com.newtory.todolist.web.dto.daily.DailyTodoSaveDto;
import com.newtory.todolist.web.dto.monthly.MonthlyTodoSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TodoController {

    private final TodoService todoService;
    private final MemberService memberService;

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
}
