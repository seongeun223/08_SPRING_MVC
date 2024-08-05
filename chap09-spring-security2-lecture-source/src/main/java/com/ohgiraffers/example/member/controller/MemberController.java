package com.ohgiraffers.example.member.controller;

import com.ohgiraffers.example.member.model.dto.SignupDTO;
import com.ohgiraffers.example.member.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor // 기본 생성자 생성
@Slf4j
public class MemberController {

    private final MemberService memberService;

    // 회원가입 페이지로 이동
    @GetMapping("/register")
    public String register() {return "member/signup";}

    @PostMapping("/register")
    public String register(SignupDTO signupDTO) {

        log.info("signup : {}", signupDTO);

        memberService.register(signupDTO);
        return "redirect:/";

    }
}
