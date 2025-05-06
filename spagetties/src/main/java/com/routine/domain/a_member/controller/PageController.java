package com.routine.web;

import com.routine.domain.a_member.dto.MemberDto;
import com.routine.domain.a_member.dto.SignupRequestDto;
import com.routine.domain.a_member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final MemberService memberService;

    // 회원정보 보기 페이지
    @GetMapping("/user/info")
    public String userInfo(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        MemberDto member = memberService.findByLoginId(userDetails.getUsername());
        model.addAttribute("member", member);
        return "user/info"; // templates/user/info.html
    }

    // 회원정보 수정 폼 페이지
    @GetMapping("/user/edit")
    public String editForm(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        MemberDto member = memberService.findByLoginId(userDetails.getUsername());
        model.addAttribute("member", member);
        return "user/edit"; // templates/user/edit.html
    }

    // 회원정보 수정 처리
    @PostMapping("/user/edit")
    public String updateInfo(@ModelAttribute SignupRequestDto dto, @AuthenticationPrincipal UserDetails userDetails) {
        String loginId = userDetails.getUsername();
        Long memberId = memberService.findByLoginId(loginId).getId();
        memberService.updateMember(memberId, dto);
        return "redirect:/user/info";
    }

    // 회원탈퇴 확인 페이지
    @GetMapping("/user/delete")
    public String deleteConfirm() {
        return "user/delete"; // templates/user/delete.html
    }

    // 회원탈퇴 처리
    @PostMapping("/user/delete")
    public String deleteAccount(@AuthenticationPrincipal UserDetails userDetails) {
        String loginId = userDetails.getUsername();
        Long memberId = memberService.findByLoginId(loginId).getId();
        memberService.deleteMember(memberId);
        return "redirect:/logout";
    }


}
