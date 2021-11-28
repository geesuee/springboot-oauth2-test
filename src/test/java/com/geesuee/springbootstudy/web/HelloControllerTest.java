package com.geesuee.springbootstudy.web;

import com.geesuee.springbootstudy.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
// 테스트를 진행할 때 JUnit 에 내장된 실행자 외에 다른 실행자를 실행시킴
// 여기에서는 SpringRunner 라는 스프링 실행자를 실행시킴
// 즉, 스프링부트와 JUnit 사이의 연결자 역할
@WebMvcTest(controllers = HelloController.class)
// 여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션
// 해당 애노테이션을 선언할 경우, @Controller, @ControllerAdvice 등을 사용할 수 있음
// 단, @Service, @Component, @Repository 등은 사용할 수 없음
public class HelloControllerTest {

    @Autowired
    // 스프링이 관리하는 빈(Bean)을 주입 받음
    private MockMvc mvc;
    // 웹 API를 테스트할 때 사용
    // 스프링 MVC 테스트의 시작점
    // 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있음

    @Test
    public void returnHello() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))          // MockMvc mvc 를 통해 /hello 주소로 HTTP GET 요청, 체이닝으로 아래 검증 기능 이어서 선언
                .andExpect(status().isOk())           // mvc.perform 의 결과를 검증, Status 검증, OK 즉, 200인지 검증
                .andExpect(content().string(hello));  // mvc.perform 의 결과를 검증, 응답 본문의 내용 검증, "hello" 를 리턴하는지 검증
    }

    @Test
    public void returnHelloDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                        .param("name", name)                            // API 테스트할 때 사용될 요청 파라미터 설정
                        .param("amount", String.valueOf(amount)))       // 단, 값은 String 만 허용, 다른 타입은 String.valueOf 로 변경해야함
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name", is(name)))        // JSON 응답값을 필드별로 검증할 수 있는 메소드
                    .andExpect(jsonPath("$.amount", is(amount)));   // $ 를 기준으로 필드명을 명시
    }
}
