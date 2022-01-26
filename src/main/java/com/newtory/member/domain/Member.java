package com.newtory.member.domain;

import com.newtory.todolist.domain.Todo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "member")
    private List<Todo> dailyTodos = new ArrayList<>();

    public Member(String name) {
        this.name = name;
    }
}
