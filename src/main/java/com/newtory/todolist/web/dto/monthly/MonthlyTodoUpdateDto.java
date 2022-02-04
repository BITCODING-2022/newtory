package com.newtory.todolist.web.dto.monthly;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MonthlyTodoUpdateDto {
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public MonthlyTodoUpdateDto(String title, String description, LocalDateTime startDate, LocalDateTime endDate) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
