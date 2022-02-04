package com.newtory.todolist.web.dto.daily;

import com.newtory.todolist.domain.DailyTodo;
import com.newtory.todolist.domain.FinishStatus;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DailyTodoResponseDto {
    private Long id;
    private String title;
    private String description;
    private FinishStatus status;
    private LocalDateTime startDate;

    public DailyTodoResponseDto(DailyTodo entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.status = entity.getStatus();
        this.startDate = entity.getStartDate();
    }
}
