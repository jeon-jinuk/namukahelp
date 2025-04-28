package com.routine.domain.a_member.controller;

import com.routine.domain.a_member.dto.SignupRequestDto;
import com.routine.domain.a_member.model.Member;
import com.routine.domain.a_member.service.MemberService;
import com.routine.security.model.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    // 관리자용 회원 전체 조회
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/members") //
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(memberService.findAllMembers());
    }

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequestDto dto) {
        memberService.registerMember(dto);
        return ResponseEntity.ok("회원가입 성공");
    }

    // 회원 정보 조회
    @GetMapping("/me")
    public ResponseEntity<Member> getMyInfo(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        Member member = principalDetails.getMember();
        return ResponseEntity.ok(member);
    }

    // 회원 정보 수정
    @PutMapping("/me")
    public ResponseEntity<String> updateMyInfo(
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @RequestBody SignupRequestDto dto
    ) {
        Long memberId = principalDetails.getMember().getId();
        memberService.updateMember(memberId, dto);
        return ResponseEntity.ok("회원 정보 수정 완료");
    }

    // 회원 탈퇴
    @DeleteMapping("/me")
    public ResponseEntity<String> deleteMyAccount(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long memberId = principalDetails.getMember().getId();
        memberService.deleteMember(memberId);
        return ResponseEntity.ok("회원 탈퇴 완료");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/{memberId}")
    public ResponseEntity<String> deleteMemberByAdmin(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.ok("회원 탈퇴 완료 (관리자)");
    }



}
