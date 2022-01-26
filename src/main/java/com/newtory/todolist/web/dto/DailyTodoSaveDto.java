package com.newtory.todolist.web.dto;

import com.newtory.member.domain.Member;
import com.newtory.todolist.domain.FinishStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class DailyTodoSaveDto {

    private Member member;
    private String title;
    private String description;
    private FinishStatus status;
    private Date startDate;

    public DailyTodoSaveDto(Member member, String title, String description, FinishStatus status, Date startDate) {
        this.member = member;
        this.title = title;
        this.description = description;
        this.status = status;
        this.startDate = startDate;
    }
}
