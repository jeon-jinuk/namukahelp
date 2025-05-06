package com.routine.controller;

import com.routine.domain.a_member.dto.SignupRequestDto;
import com.routine.domain.a_member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final MemberService memberService;

    // 로그인 페이지
    @GetMapping("/user/login")
    public String loginForm() {
        return "user/login";
    }

    // 홈 페이지
    @GetMapping("/")
    public String home() {
        return "home";
    }

    // 회원가입 페이지
    @GetMapping("/user/join")
    public String joinForm() {
        return "user/join";
    }

    // 회원가입 처리
    @PostMapping("/user/join")
    public String register(SignupRequestDto dto) {
        memberService.registerMember(dto);
        return "redirect:/user/login"; // 회원가입 성공 시 로그인 페이지로 이동
    }
}
