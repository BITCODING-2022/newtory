package com.newtory.todolist.domain;

import com.newtory.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@DiscriminatorValue("M")
@NoArgsConstructor
@Entity
public class MonthlyTodo extends Todo{

    private LocalDateTime endDate;

    @Builder
    public MonthlyTodo(Member member, String title, String description, FinishStatus status, LocalDateTime startDate, LocalDateTime endDate) {
        super(member, title, description, status, startDate);
        this.endDate = endDate;
    }
}
