package com.newtory.member.web;

import com.newtory.member.domain.Member;
import com.newtory.member.service.MemberService;
import com.newtory.member.web.dto.MemberJoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Column;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/join")
    public Long join(@RequestBody MemberJoinDto dto) {
        Member member = new Member(dto.getName());
        return memberService.join(member);
    }


}
