package com.newtory.todolist.domain;

import com.newtory.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
@DiscriminatorValue("D")
@Entity
public class DailyTodo extends Todo{

    public DailyTodo(Member member,
                     String title,
                     String description,
                     FinishStatus status,
                     LocalDateTime startDate) {
        super(member, title, description, status, startDate);
    }

    @Override
    public void update(String title,
                       String description,
                       LocalDateTime startDate) {
        super.update(title, description, startDate);
    }

}
