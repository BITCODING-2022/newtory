package com.newtory.member.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberJoinDto {

    private String name;

    public MemberJoinDto(String name) {
        this.name = name;
    }
}
