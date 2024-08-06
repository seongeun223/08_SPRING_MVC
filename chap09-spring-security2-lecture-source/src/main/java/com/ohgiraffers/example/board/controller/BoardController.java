package com.ohgiraffers.example.board.controller;

import com.ohgiraffers.example.board.model.dto.CreateBoardDTO;
import com.ohgiraffers.example.board.model.entity.Board;
import com.ohgiraffers.example.board.service.BoardService;
import com.ohgiraffers.example.member.model.entity.Member;
import com.ohgiraffers.example.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    private final MemberService memberService;
    private final BoardService boardService;

    @GetMapping("/create")
    public String create() {
        return "board/createBoard";
    }

    @PostMapping("/create")
    public String createPost(@AuthenticationPrincipal UserDetails userDetails, CreateBoardDTO createBoardDTO) {

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String memberIdAuthentication = authentication.getName();
//        log.info("Authentication에서 꺼낸 사용자 ID : {}", memberIdAuthentication);

        String memberId = userDetails.getUsername();

        // member 찾기
        Member member = memberService.findMemberById(memberId);

        log.info("로그인한 사용자 ID : {}", memberId);
        log.info("전달받은 BoardDTO : {}", createBoardDTO);

        boardService.crete(createBoardDTO, member);
        return "redirect:/";
    }

    @GetMapping("/{boardId}")
    public String getBoardDetail(@PathVariable int boardId, Model model) {

        Board board = boardService.findBoardById(boardId);

        model.addAttribute("board", board);

        return "board/detail";
    }
}
