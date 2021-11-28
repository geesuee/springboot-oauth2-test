package com.geesuee.springbootstudy.config.auth;

import com.geesuee.springbootstudy.web.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
// Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // csrf().disable().headers().frameOptions().disable()
            // 콘솔 화면을 사용하기 위해 해당 옵션들을 disable
        // authorizseRequests
            // URL 별 권한 관리를 설정하는 옵션의 시작점
            // authorizeRequests 가 선언되어야만 antMatchers 옵션 사용 가능
        // antMatchers
            // 권한 관리 대상을 지정하는 옵션
            // URL, HTTP 메소드별로 관리 가능
            // "/" 등 지정된 URL 들은 permitAll() 옵션을 통해 전체 열람 권한 부여
            // "/api/v1/**" 주소를 가진 API 는 USER 권한을 가진 사람만 열람 가능
        // anyRequest
            // 설정된 값들 이외 나머지 URL 의미
            // authenticated() 로 나머지 URL 들은 모두 인증된 사용자들에게만 허용
        // logout().logoutSuccessUrl("/")
            // 로그아웃 기능에 대한 설정의 시작점
        // userInfoEndpoint
            // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정 담당
        // userService
            // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체 등록
            // 리소스 서버(즉, 소셜 서비스)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자하는 기능 명시 가능


        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/", "/css/**", "/images/**", "/js/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService);

    }
}
