package com.newtory.todolist.domain;

import com.newtory.member.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Todo {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    private String title;
    private String description;


    private FinishStatus status;

    private LocalDateTime startDate;

    public Todo(Member member, String title, String description, FinishStatus status, LocalDateTime startDate) {
        this.member = member;
        this.title = title;
        this.description = description;
        this.status = status;
        this.startDate = startDate;
    }
}
