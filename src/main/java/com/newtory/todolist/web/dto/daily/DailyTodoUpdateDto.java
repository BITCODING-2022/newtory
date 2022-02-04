package com.newtory.todolist.web.dto.daily;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class DailyTodoUpdateDto {
    private String title;
    private String description;
    private LocalDateTime startDate;

    public DailyTodoUpdateDto(String title, String description, LocalDateTime startDate) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
    }
}
