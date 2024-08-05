package com.ohgiraffers.example.auth.service;

import com.ohgiraffers.example.auth.principal.AuthPrincipal;
import com.ohgiraffers.example.member.model.entity.Member;
import com.ohgiraffers.example.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthService implements UserDetailsService {

    private final MemberRepository memberRepository;

    // 회원 정보를 조회하여, 일치하는지 확인하는 로직이 들어가야 함.
    // 일치한다면, UserDetails 객체를 반환해주는 메소드
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findMemberByMemberId(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        log.info("로그인하는 회원 이름 : {}", member.getName());

        return new AuthPrincipal(member);
    }
}
