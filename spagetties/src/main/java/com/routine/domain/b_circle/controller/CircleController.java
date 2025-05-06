package com.routine.domain.b_circle.controller;

import com.routine.domain.b_circle.dto.CircleCreateRequestDto;
import com.routine.domain.b_circle.model.Circle;
import com.routine.domain.b_circle.model.CircleMember;
import com.routine.domain.b_circle.service.CircleService;
import com.routine.security.model.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;
import com.routine.domain.a_member.model.Member;
import com.routine.domain.b_circle.dto.MyCircleJoinStatusDto;




@RestController
@RequiredArgsConstructor
@RequestMapping("/api/circles")
public class CircleController {

    private final CircleService circleService;

    @PostMapping
    public ResponseEntity<String> createCircle(
            @RequestBody CircleCreateRequestDto dto,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        Long memberId = principalDetails.getMember().getId();
        circleService.createCircle(dto, memberId);
        return ResponseEntity.ok("그룹이 성공적으로 생성되었습니다.");
    }

    @PostMapping("/{id}/join")
    public ResponseEntity<String> joinCircle(
            @PathVariable("id") Long circleId,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        Long memberId = principalDetails.getMember().getId();
        circleService.joinCircle(circleId, memberId);
        return ResponseEntity.ok("써클 가입이 완료되었습니다.");
    }
    @PutMapping("/{circleId}")
    public ResponseEntity<String> updateCircle(
            @PathVariable Long circleId,
            @AuthenticationPrincipal PrincipalDetails principalDetails,
            @ModelAttribute CircleCreateRequestDto dto
    ) {
        circleService.updateCircle(circleId, principalDetails.getMember().getId(), dto);
        return ResponseEntity.ok("써클이 수정되었습니다.");
    }

    @DeleteMapping("/{circleId}")
    public ResponseEntity<String> deleteCircle(
            @PathVariable Long circleId,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        circleService.deleteCircle(circleId, principalDetails.getMember().getId());
        return ResponseEntity.ok("써클이 삭제되었습니다.");
    }

    @GetMapping
    public ResponseEntity<List<Circle>> getAllCircles() {
        return ResponseEntity.ok(circleService.findAllCircles());
    }



    @GetMapping("/view/{id}")
    public ResponseEntity<Circle> getCircle(@PathVariable("id") Long circleId) {
        return ResponseEntity.ok(circleService.findCircleById(circleId));
    }
    @DeleteMapping("/{id}/leave")
    public ResponseEntity<String> leaveCircle(
            @PathVariable("id") Long circleId,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        Long memberId = principalDetails.getMember().getId();     // 탈퇴 대상 (본인)
        Long requesterId = principalDetails.getMember().getId();  // 요청자 = 본인

        circleService.leaveCircle(circleId, memberId, requesterId);
        return ResponseEntity.ok("써클에서 탈퇴했습니다.");
    }

    @GetMapping("/{circleId}/members")
    public ResponseEntity<List<CircleMember>> getCircleMembers(
            @PathVariable Long circleId,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        Long memberId = principalDetails.getMember().getId();
        return ResponseEntity.ok(circleService.getMembersInCircle(circleId, memberId));
    }
    @PostMapping("/{circleId}/approve/{memberId}")
    public ResponseEntity<String> approveCircleMember(
            @PathVariable Long circleId,
            @PathVariable Long memberId,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        Long approverId = principalDetails.getMember().getId();
        circleService.approveCircleMember(circleId, memberId, approverId);
        return ResponseEntity.ok("신청되었습니다.");
    }

    @PostMapping("/{circleId}/reject/{memberId}")
    public ResponseEntity<String> rejectCircleMember(
            @PathVariable Long circleId,
            @PathVariable Long memberId,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        Long adminId = principalDetails.getMember().getId();
        circleService.rejectCircleMember(circleId, memberId, adminId);
        return ResponseEntity.ok("가입이 거절되었습니다.");
    }
    @PutMapping("/{circleId}/transfer/{newLeaderId}")
    public ResponseEntity<String> transferLeader(
            @PathVariable Long circleId,
            @PathVariable Long newLeaderId,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        Long currentLeaderId = principalDetails.getMember().getId();
        circleService.transferLeadership(circleId, currentLeaderId, newLeaderId);
        return ResponseEntity.ok("써클장이 변경되었습니다.");
    }
    @GetMapping("/search")
    public ResponseEntity<List<Map<String, Object>>> searchCircles(
            @RequestParam(required = false) Boolean isPublic,
            @RequestParam(required = false) String keyword,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        Long memberId = principalDetails.getMember().getId();
        List<Circle> results = circleService.searchCircles(isPublic, keyword);

        List<Map<String, Object>> response = results.stream().map(circle -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", circle.getId());
            map.put("name", circle.getName());
            map.put("description", circle.getDescription());
            map.put("tags", String.join(", ", circle.getTags()));
            map.put("public", circle.isPublic());

            // 추가: 이미 가입했는지 여부
            boolean alreadyJoined = circle.getMembers().stream()
                    .anyMatch(cm -> cm.getMember().getId().equals(memberId));
            map.put("alreadyJoined", alreadyJoined);

            return map;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
    @GetMapping("/api/circle-joins/my")
    public ResponseEntity<List<MyCircleJoinStatusDto>> getMyJoinStatusList(
            @AuthenticationPrincipal Member member,
            @RequestParam(required = false) String status) {

        List<MyCircleJoinStatusDto> list = circleService.getMyJoinStatusList(member.getId(), status);
        return ResponseEntity.ok(list);
    }



















}
