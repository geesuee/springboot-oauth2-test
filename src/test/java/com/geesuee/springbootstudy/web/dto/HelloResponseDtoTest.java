package com.geesuee.springbootstudy.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void testLombok() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
        // assertThat
        // assertj 라는 테스트 검증 라이브러리의 검증 메소드
        // 검증하고 싶은 대상을 메소드 인자로 받음
        // 메소드 체이닝이 지원되어 isEqualTo 와 같은 메소드를 이어서 사용 가능

        // isEqualTo
        // assertj 의 동등 비교 메소드
        // assertThat 에 있는 값과 isEqualTo 의 값을 비교해서 같을 때만 성공
    }
}
