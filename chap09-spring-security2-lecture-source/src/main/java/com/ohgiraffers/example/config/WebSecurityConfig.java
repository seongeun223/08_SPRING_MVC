package com.ohgiraffers.example.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    // security에서 특정 경로를 보안 검증해서 제외하는 코드
    // css, js, 이미지 같은 정적 자원들에 대해 보안 필터를 적용하지 않게 함
    @Bean
    public WebSecurityCustomizer securityCustomizer() {
//        return new WebSecurityCustomizer() {
//            @Override
//            public void customize(WebSecurity web) {
//                web.ignoring().requestMatchers("/css/**", "/js/**", "/images/**");
//            }
//        };

        return (web) -> web.ignoring().requestMatchers(PathRequest
                .toStaticResources()
                .atCommonLocations());

        // 위에 주석과 같은 의미
    }


    // Spring Security에서 제공하는 인증, 인가를 위한 필터들의 모음
    // 기본적으로 제공하는 필터들이 있고, 커스텀 필터 또한 적용시킬 수 있다.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((autorizationManagerRequsetMatcherRegistry -> {
            autorizationManagerRequsetMatcherRegistry
                    .requestMatchers("/", "index.html").permitAll() // 모두에게 허용
                    .requestMatchers("/member/register").anonymous() // 비인증사용자만 접근
                    .anyRequest().authenticated(); // 인증된 사용자만 요청 가능
        }));

        // formLogin 설정
        http.formLogin((formLoginConfigurer -> {
            formLoginConfigurer
                    .loginPage("/auth/login") // 로그인 페이지(GET)
                    .loginProcessingUrl("/auth/login") // 로그인 처리(POST)
                    .usernameParameter("memberId") // userName으로 전달할 파라미터 설정
                    .passwordParameter("password") // passWord로 전달할 파라미터 설정
                    .defaultSuccessUrl("/") // 로그인 성공 시 이동할 url
                    .permitAll();
        }));

        return http.build();
    }

    // 비밀번호 암호화
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
