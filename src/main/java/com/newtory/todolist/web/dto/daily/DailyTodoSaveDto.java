package com.newtory.todolist.web.dto.daily;

import com.newtory.member.domain.Member;
import com.newtory.todolist.domain.FinishStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
public class DailyTodoSaveDto {

    private String title;
    private String description;
    private FinishStatus status;
    private LocalDateTime startDate;

    public DailyTodoSaveDto(String title, String description, FinishStatus status, LocalDateTime startDate) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.startDate = startDate;
    }
}
