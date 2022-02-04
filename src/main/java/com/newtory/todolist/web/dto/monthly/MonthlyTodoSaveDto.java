package com.newtory.todolist.web.dto.monthly;

import com.newtory.member.domain.Member;
import com.newtory.todolist.domain.FinishStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
public class MonthlyTodoSaveDto {

    private String title;
    private String description;
    private FinishStatus status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public MonthlyTodoSaveDto(String title, String description, FinishStatus status, LocalDateTime startDate, LocalDateTime endDate) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
