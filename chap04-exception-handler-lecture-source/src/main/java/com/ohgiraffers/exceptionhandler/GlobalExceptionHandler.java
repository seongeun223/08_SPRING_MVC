package com.ohgiraffers.exceptionhandler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

// 전역 예외 처리를 담당
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(NullPointerException exception) {

        System.out.println("Global 레벨의 exception 처리");

        return "error/nullPointer";
    }

    @ExceptionHandler(MemberRegistException.class)
    public String userExceptionHandler(Model model, MemberRegistException exception) {

        System.out.println("Global 레벨의 exception 처리");
        model.addAttribute("exception", exception);

        return "error/memberRegist";
    }
}
