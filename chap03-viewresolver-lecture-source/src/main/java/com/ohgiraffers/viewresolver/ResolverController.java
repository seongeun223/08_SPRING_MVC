package com.ohgiraffers.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/*")
public class ResolverController {

    @GetMapping("string")
    public String stringReturning(Model model) {
        model.addAttribute("forwardMessage", "문자열로 뷰 이름 반환");

        /*
        * 문자열로 뷰 이름을 반환한다는 것은 반환 후
        * ThymeleafViewResolver에게
        * resources/templates/ 를 prefix로 지정하고
        * .html 을 suffix 로 하여
        * resources/templates/result.html 파일로 응답뷰로 설정하라는 의미
        * */

        return "result";
    }

    @GetMapping("string-redirect")
    public String stringRedirect() {

        // redirect: 접두사를 붙이면 forward가 아닌 redirect를 한다.
        System.out.println("리다이렉트 호출됨");

        return "redirect:/";
    }

    @GetMapping("string-redirect-attr")
    public String stringRedirectFlashAttribute(RedirectAttributes rttr) {

        /*
        * 리다이렉트 시 flash 영역에 담아서 redirect 할 수 있다.
        * 자동으로 모델에 추가되기 때문에 requestScope 에서 꺼내서 사용할 수 있다.
        *
        * 주의!
        * 세션에 임시로 값을 담고 소멸하는 방식이기 때문에
        * session에 동일한 키 값이 존재하면 안 된다.
        * */

        rttr.addFlashAttribute("flashMessage1", "리다이렉트 attr 사용하여 redirect...");

        return "redirect:/";
    }

    @GetMapping("modelandview")
    public ModelAndView modelAndViewReturning(ModelAndView mv) {

        /*
        * ModelAndView
        * - 모델과 뷰를 합친 개념
        * - 핸들러 어뎁터가 핸들러 메소드를 호출하고 반환받은 문자열을 ModelAndView로 만들어
        *   DispatcherServlet에 반환한다.
        *   이 때, 문자열을 반환해도 되고, ModelAndView 객체를 만들어서 반환할 수도 있다.
        * */

        // 속성값 담기
        mv.addObject("forwardMessage",
                "ModelAndView를 이용한 모델과 뷰 반환");

        // 뷰 이름 담기
        mv.setViewName("result");

        return mv;

    }

    @GetMapping("modelandview-redirect")
    public ModelAndView modelAndViewRedirect(ModelAndView mv) {

        mv.setViewName("redirect:/");
        System.out.println("리다이렉트 호출됨");

        return mv;
    }

    @GetMapping("modelandview-redirect-attr")
    public ModelAndView modelAndViewRedirectAttr(ModelAndView mv, RedirectAttributes rttr) {

        rttr.addFlashAttribute("flashMessage2",
                "ModelAndView를 이용한 redirect attr");

        mv.setViewName("redirect:/");

        return mv;
    }
}
