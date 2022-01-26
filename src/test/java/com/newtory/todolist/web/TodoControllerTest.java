package com.newtory.todolist.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.newtory.member.domain.Member;
import com.newtory.member.repository.MemberRepository;
import com.newtory.todolist.domain.FinishStatus;
import com.newtory.todolist.domain.Todo;
import com.newtory.todolist.repository.TodoRepository;
import com.newtory.todolist.service.TodoService;
import com.newtory.todolist.web.dto.daily.DailyTodoSaveDto;
import com.newtory.todolist.web.dto.monthly.MonthlyTodoSaveDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TodoControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoService todoService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MockMvc mvc;

    @AfterEach
    public void tearDown() {
        todoRepository.deleteAll();
    }

    @Test
    public void Daily_추가() throws Exception {
        // given
        Member member = new Member("user");
        memberRepository.save(member);
        DailyTodoSaveDto dailyDto = new DailyTodoSaveDto(
                "title",
                "description",
                FinishStatus.ON_GOING,
                LocalDateTime.now());

        String url = "http://localhost:" + port + "/" + member.getId() + "/save/daily";

        // when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().registerModules(new JavaTimeModule()).writeValueAsString(dailyDto)))
                .andExpect(status().isOk());

        // then
        List<Todo> all = todoRepository.findAll();
        Assertions.assertThat(all.get(0).getTitle()).isEqualTo("title");
        Assertions.assertThat(all.get(0).getDescription()).isEqualTo("description");
    }

    @Test
    public void Monthly_추가() throws Exception {
        // given
        Member member = new Member("user");
        memberRepository.save(member);
        MonthlyTodoSaveDto monthlyDto = new MonthlyTodoSaveDto(
                "title",
                "description",
                FinishStatus.ON_GOING,
                LocalDateTime.now(),
                LocalDateTime.now());

        String url = "http://localhost:" + port + "/" + member.getId() + "/save/monthly";

        // when
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().registerModules(new JavaTimeModule()).writeValueAsString(monthlyDto)))
                .andExpect(status().isOk());

        // then
        List<Todo> all = todoRepository.findAll();
        Assertions.assertThat(all.get(0).getTitle()).isEqualTo("title");
        Assertions.assertThat(all.get(0).getDescription()).isEqualTo("description");
    }

    @Test
    public void Todo_삭제() throws Exception {
        // given
        Member member = new Member("user");
        memberRepository.save(member);
        MonthlyTodoSaveDto monthlyDto = new MonthlyTodoSaveDto(
                "title",
                "description",
                FinishStatus.ON_GOING,
                LocalDateTime.now(),
                LocalDateTime.now());

        Long saveId = todoService.addMonthlyTodo(member, monthlyDto);

        String url = "http://localhost:" + port + "/" + member.getId() + "/" + saveId;

        // when
        mvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().registerModules(new JavaTimeModule()).writeValueAsString(monthlyDto)))
                .andExpect(status().isOk());

        // then
        List<Todo> all = todoRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(0);
    }
}