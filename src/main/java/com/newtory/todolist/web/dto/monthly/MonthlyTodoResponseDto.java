package com.newtory.todolist.web.dto.monthly;

import com.newtory.todolist.domain.DailyTodo;
import com.newtory.todolist.domain.FinishStatus;
import com.newtory.todolist.domain.MonthlyTodo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MonthlyTodoResponseDto {
    private Long id;
    private String title;
    private String description;
    private FinishStatus status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public MonthlyTodoResponseDto(MonthlyTodo entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.status = entity.getStatus();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
    }
}
