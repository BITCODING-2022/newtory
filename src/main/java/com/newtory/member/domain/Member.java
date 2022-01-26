package com.newtory.member.domain;

import com.newtory.todolist.domain.Todo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @OneToMany(mappedBy = "member")
    private List<Todo> dailyTodos = new ArrayList<>();

}
