package com.ohgiraffers.requestmapping;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * 클래스 레벨에 @RequestMapping 어노테이션 사용이 가능하다.
 * 클래스레벨에 URL을 공통 부분을 이용해 설정하고 나면 매번 핸들러 메소드에 URL의
 * 중복되는 내용을 작성하지 않아도 된다.
 * 이 때 와일드 카드를 이용해서 좀 더 포괄적인 URL 패턴을 설정할 수 있다.
 * */

@Controller
@RequestMapping("/order/*")
public class ClassMappingTestController {

    // 1. 클래스 레벨 매핑
    @GetMapping("/regist")
    public String registOrder(Model model) {

        model.addAttribute("message", "GET 방식의 주문 등록용 핸들러 메소드 호출함...");

        return "page/mappingResult";
    }

    // 2. 여러 개의 패턴 매핑
    @RequestMapping(value = {"modify", "delete"}, method = RequestMethod.POST)
    public String modifyAndDelete(Model model) {

        model.addAttribute("message", "POST 방식의 주문 정보 수정과 주문 정보 삭제 공통 처리용 핸들러 메소드 호출함...");

        return "page/mappingResult";
    }

    /*
    * path variable
    * @PathVariable 어노테이션을 이용해 요청 path로부터 변수를 받아올 수 있다.
    * path variable로 전달되는 {변수명} 값은 반드시 매개변수 명이랑 같아야 한다.
    * 만약, 동일하지 않으면 @PathVariable("이름")을 설정해주어야 한다.
    * */

    @GetMapping("/detail/{orderNo}")
    public String selectOrderDetail(Model model, @PathVariable int orderNo) {

        model.addAttribute("message",
                orderNo + " 번 주문 상세 내용 조회용 핸들러 메소드 호출함...");

        return "page/mappingResult";
    }


}
