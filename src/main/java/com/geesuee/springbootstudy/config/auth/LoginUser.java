package com.geesuee.springbootstudy.config.auth;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Target(ElementType.PARAMETER)
// 이 어노테이션이 생성될 수 있는 위치 지정
// PARAMETER 로 지정했기 때문에 메소드의 파라미터로 선언된 객체에만 사용 가능
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
    // 이 파일을 어노테이션 클래스로 지정
    // LoginUser 라는 이름을 가진 어노테이션 생성
}
