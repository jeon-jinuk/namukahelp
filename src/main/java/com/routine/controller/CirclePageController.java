package com.routine.controller;

import com.routine.domain.b_circle.service.CircleInvitationService;
import com.routine.domain.b_circle.service.CircleMemberService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.routine.dto.CircleDto;
import com.routine.domain.b_circle.service.CircleService;
import com.routine.security.model.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import com.routine.domain.b_circle.dto.CircleCreateRequestDto;
import com.routine.domain.b_circle.model.Circle;
import com.routine.domain.b_circle.model.CircleMember;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequiredArgsConstructor
@RequestMapping("/circle")
public class CirclePageController {

    private final CircleService circleService;
    private final CircleInvitationService invitationService;
    private final CircleMemberService circleMemberService;

    @GetMapping("/create")
    public String showCreatePage() {
        return "circle/create";
    }

    @GetMapping("/list")
    public String showCircleListPage() {
        return "circle/list";
    }


    @GetMapping("/my")
    public String showMyCirclesPage(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long memberId = principalDetails.getMember().getId();
        String memberNickname = principalDetails.getMember().getNickname();


        List<Map<String, Object>> myCircles = circleService.findCirclesByMemberId(memberId).stream()
                .map(circle -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", circle.getId());
                    map.put("name", circle.getName());
                    map.put("description", circle.getDescription());
                    map.put("tags", String.join(", ", circle.getTags()));
                    map.put("public", circle.isPublic());
                    map.put("editable", circleService.canEditCircle(circle.getId(), memberId));
                    return map;
                }).collect(Collectors.toList());

        model.addAttribute("myCircles", myCircles);
        model.addAttribute("memberNickname", memberNickname);
        return "circle/my"; //
    }


    @GetMapping("/edit/{id}")
    public String showEditPage(@PathVariable Long id, Model model) {
        CircleDto circle = circleService.getCircleDtoById(id);
        model.addAttribute("circle", circle);
        return "circle/edit";
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCircle(
            @PathVariable Long id,
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @ModelAttribute CircleCreateRequestDto dto  //
    ) {
        Long memberId = principalDetails.getMember().getId();
        circleService.updateCircle(id, memberId, dto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/members/manage/{circleId}")
    public String showMembersPage(@PathVariable Long circleId, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long requesterId = principalDetails.getMember().getId();
        Circle circle = circleService.findCircleById(circleId);

        List<CircleMember> all = circle.getMembers();


        for (CircleMember cm : all) {
            if (cm.getMember() != null) {
                cm.getMember().getNickname();  // nickname 접근해서 강제 로딩
            }
        }

        List<CircleMember> approved = all.stream()
                .filter(cm -> cm.getStatus() == CircleMember.Status.APPROVED)
                .collect(Collectors.toList());

        List<CircleMember> pending = all.stream()
                .filter(cm -> cm.getStatus() == CircleMember.Status.PENDING)
                .collect(Collectors.toList());

        boolean isLeader = circle.getCreatorId().equals(requesterId);

        String memberNickname = principalDetails.getMember().getNickname();

        model.addAttribute("circleId", circleId);
        model.addAttribute("approvedMembers", approved);
        model.addAttribute("pendingMembers", pending);
        model.addAttribute("isLeader", isLeader);
        model.addAttribute("memberNickname", memberNickname);

        return "circle/manageMembers";
    }


    @PostMapping("/{id}/join")
    public String joinCircle(
            @PathVariable Long id,
            @RequestParam String memberNickname,
            RedirectAttributes redirectAttributes
    ) {
        circleService.requestJoinCircleByNickname(id, memberNickname);
        redirectAttributes.addFlashAttribute("message", "가입 요청 완료되었습니다.");
        return "redirect:/circle/my";
    }
    @GetMapping("/invitations")
    public String viewInvitationsPage(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long memberId = principalDetails.getMember().getId();
        List<Map<String, Object>> invitations = invitationService.getPendingInvitations(memberId).stream()
                .map(inv -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("circleId", inv.getCircle().getId());
                    map.put("circleName", inv.getCircle().getName());
                    map.put("description", inv.getCircle().getDescription());
                    return map;
                }).collect(Collectors.toList());

        model.addAttribute("invitations", invitations);
        model.addAttribute("memberId", memberId);
        return "circle/invitations";
    }
    @GetMapping("/{circleId}")
    public String viewCircleDetail(@PathVariable Long circleId, Model model) {
        CircleDto circle = circleService.getCircleDtoById(circleId);
        model.addAttribute("circle", circle);
        return "circle/detail";  // templates/circle/detail.html
    }
    @GetMapping("/members/{circleId}")
    public String showCircleMembers(@PathVariable Long circleId, Model model) {
        Circle circle = circleService.findCircleById(circleId);
        List<CircleMember> members = circleMemberService.getMembersByCircleId(circleId);
        model.addAttribute("circle", circle);
        model.addAttribute("members", members);
        return "circle/members";  // templates/circle/members.html
    }
    // 승인 (approve)
    @PostMapping("/members/manage/{circleId}/approve")
    public String approveMember(
            @PathVariable Long circleId,
            @RequestParam Long memberId
    ) {
        circleMemberService.approveMember(circleId, memberId);
        return "redirect:/circle/members/manage/" + circleId;
    }

    // 거절 (reject)
    @PostMapping("/members/manage/{circleId}/reject")
    public String rejectMember(
            @PathVariable Long circleId,
            @RequestParam Long memberId
    ) {
        circleMemberService.rejectMember(circleId, memberId);
        return "redirect:/circle/members/manage/" + circleId;
    }
    @GetMapping("/manage/{id}")
    public String manageCirclePage(@PathVariable Long id, Model model) {
        CircleDto circle = circleService.getCircleDtoById(id);
        model.addAttribute("circle", circle);
        return "circle/manageCircle";  // templates/circle/manageCircle.html
    }











}
