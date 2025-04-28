// src/main/java/com/routine/domain/b_circle/controller/CircleInvitationController.java
package com.routine.domain.b_circle.controller;

import com.routine.domain.a_member.model.Member;
import com.routine.domain.a_member.model.Role;
import com.routine.domain.a_member.repository.MemberRepository;
import com.routine.domain.b_circle.model.Circle;
import com.routine.domain.b_circle.model.CircleInvitation;
import com.routine.domain.b_circle.repository.CircleRepository;
import com.routine.domain.b_circle.service.CircleInvitationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;






@RestController
@RequestMapping("/api/circles")
@RequiredArgsConstructor
public class CircleInvitationController {

    private final CircleRepository circleRepository;
    private final MemberRepository memberRepository;
    private final CircleInvitationService invitationService;

    @PostMapping("/{circleId}/invite")
    public ResponseEntity<?> inviteMember(
            @PathVariable Long circleId,
            @RequestParam String requesterNickname,
            @RequestParam String memberNickname
    ) {
        Circle circle = circleRepository.findById(circleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 써클입니다."));

        Member requester = memberRepository.findByNickname(requesterNickname)
                .orElseThrow(() -> new IllegalArgumentException("요청자 닉네임 없음"));

        if (!circle.getCreatorId().equals(requester.getId()) && requester.getRole() != Role.ADMIN) {
            return ResponseEntity.status(403).body("초대 권한이 없습니다.");
        }

        Member target = memberRepository.findByNickname(memberNickname)
                .orElseThrow(() -> new IllegalArgumentException("초대 대상 닉네임 없음"));

        invitationService.invite(target, circle);

        return ResponseEntity.ok("초대 완료");
    }




    @GetMapping("/invitations")
    public ResponseEntity<?> getInvitations(@RequestParam Long memberId) {
        List<CircleInvitation> invitations = invitationService.getPendingInvitations(memberId);

        List<Map<String, Object>> result = invitations.stream().map(inv -> {
            Map<String, Object> map = new HashMap<>();
            map.put("circleId", inv.getCircle().getId());
            map.put("circleName", inv.getCircle().getName());
            map.put("description", inv.getCircle().getDescription());
            return map;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }
    @GetMapping("/circle/invitations")
    public String viewInvitations(@RequestParam Long memberId, Model model) {
        List<CircleInvitation> invitations = invitationService.getPendingInvitations(memberId);

        List<Map<String, Object>> result = invitations.stream().map(inv -> {
            Map<String, Object> map = new HashMap<>();
            map.put("circleId", inv.getCircle().getId());
            map.put("circleName", inv.getCircle().getName());
            map.put("description", inv.getCircle().getDescription());
            return map;
        }).collect(Collectors.toList());

        model.addAttribute("invitations", result);
        model.addAttribute("memberId", memberId);
        return "circle/invitations"; // templates/invitations.html
    }
    @PostMapping("/{circleId}/accept")
    public ResponseEntity<?> acceptInvitation(
            @PathVariable Long circleId,
            @RequestParam Long memberId
    ) {
        invitationService.acceptInvitation(memberId, circleId);
        return ResponseEntity.ok("초대 수락 완료");
    }

    @PostMapping("/{circleId}/reject")
    public ResponseEntity<?> rejectInvitation(
            @PathVariable Long circleId,
            @RequestParam Long memberId
    ) {
        invitationService.rejectInvitation(memberId, circleId);
        return ResponseEntity.ok("초대 거절 완료");
    }







}
