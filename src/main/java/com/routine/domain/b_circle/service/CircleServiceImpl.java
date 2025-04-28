package com.routine.domain.b_circle.service;

import com.routine.domain.a_member.model.Member;
import com.routine.domain.a_member.model.Role;
import com.routine.domain.a_member.repository.MemberRepository;
import com.routine.domain.b_circle.dto.CircleCreateRequestDto;
import com.routine.domain.b_circle.model.Circle;
import com.routine.domain.b_circle.model.CircleMember;
import com.routine.domain.b_circle.repository.CircleMemberRepository;
import com.routine.domain.b_circle.repository.CircleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.routine.dto.CircleDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;





@Service
@RequiredArgsConstructor
public class CircleServiceImpl implements CircleService {

    private final CircleRepository circleRepository;
    private final MemberRepository memberRepository;
    private final CircleMemberRepository circleMemberRepository;
    private final CircleInvitationService invitationService;

    @Override
    public void createCircle(CircleCreateRequestDto dto, Long creatorId) {
        // 써클 이름 중복 체크
        if (circleRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("이미 존재하는 써클 이름입니다.");
        }

        // 써클 생성
        Circle circle = Circle.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .tags(dto.getTags())
                .isPublic(dto.isPublic())
                .creatorId(creatorId)
                .build();

        circleRepository.save(circle);

        // 써클 생성자를 자동으로 써클장으로 등록
        Member creator = memberRepository.findById(creatorId)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

        CircleMember leader = CircleMember.builder()
                .circle(circle)
                .member(creator)
                .role(CircleMember.Role.LEADER)
                .status(CircleMember.Status.APPROVED)
                .commitRate(0.0)
                .skipCount(0)
                .points(0)
                .build();

        circle.getMembers().add(leader);
        creator.getCircleMembers().add(leader);

        circleMemberRepository.save(leader);
    }



    @Override
    public void joinCircle(Long circleId, Long memberId) {
        Circle circle = circleRepository.findById(circleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹입니다."));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        // 비공개 그룹 초대 확인
        if (!circle.isPublic()) {
            if (!invitationService.hasValidInvitation(member, circle)) {
                throw new IllegalStateException("초대되지 않은 비공개 써클입니다.");
            }
        }

        // 중복 가입 방지
        boolean alreadyJoined = circle.getMembers().stream()
                .anyMatch(cm -> cm.getMember().getId().equals(memberId));
        if (alreadyJoined) {
            throw new IllegalStateException("이미 가입한 그룹입니다.");
        }

        // 써클 멤버 등록 (대기 상태)
        CircleMember circleMember = CircleMember.builder()
                .circle(circle)
                .member(member)
                .role(CircleMember.Role.MEMBER)
                .status(CircleMember.Status.PENDING)
                .commitRate(0.0)
                .skipCount(0)
                .points(0)
                .build();

        circle.getMembers().add(circleMember);
        member.getCircleMembers().add(circleMember);

        circleMemberRepository.save(circleMember);

        // 초대 사용 처리
        if (!circle.isPublic()) {
            invitationService.markInvitationUsed(member, circle);
        }
    }


    // 써클 수정 (생성자 or ADMIN만 가능)
    @Override
    public void updateCircle(Long circleId, Long memberId, CircleCreateRequestDto dto) {
        Circle circle = circleRepository.findById(circleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹입니다."));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        if (!circle.getCreatorId().equals(memberId) && member.getRole() != Role.ADMIN) {
            throw new SecurityException("수정 권한이 없습니다.");
        }

        circle.setName(dto.getName());
        circle.setDescription(dto.getDescription());
        circle.setTags(dto.getTags());
        circle.setPublic(dto.isPublic());

        circleRepository.save(circle);
    }

    // 써클 삭제 (생성자 or ADMIN만 가능)
    @Override
    public void deleteCircle(Long circleId, Long memberId) {
        Circle circle = circleRepository.findById(circleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 그룹입니다."));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        if (!circle.getCreatorId().equals(memberId) && member.getRole() != Role.ADMIN) {
            throw new SecurityException("삭제 권한이 없습니다.");
        }

        circleRepository.delete(circle);
    }
    @Override
    @Transactional(readOnly = true)
    public Circle findCircleById(Long id) {
        Circle circle = circleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 써클입니다."));

        if (circle.getMembers() != null) {
            for (CircleMember cm : circle.getMembers()) {
                if (cm.getMember() != null) {
                    cm.getMember().getNickname();
                }
            }
        }

        return circle;
    }



    @Override
    public List<Circle> findAllCircles() {
        return circleRepository.findAll();
    }

    @Override
    public List<Circle> findCirclesByMemberId(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        System.out.println("현재 로그인한 사용자 ID: " + member.getId());
        System.out.println("가입된 써클 수: " + member.getCircleMembers().size());

        for (CircleMember cm : member.getCircleMembers()) {
            System.out.println("🌀 써클 ID: " + cm.getCircle().getId());
            System.out.println("🌀 써클 이름: " + cm.getCircle().getName());
            System.out.println("🌀 상태: " + cm.getStatus());
        }

        return member.getCircleMembers().stream()
                .filter(cm -> cm.getStatus() == CircleMember.Status.APPROVED)
                .map(CircleMember::getCircle)
                .collect(Collectors.toList());
    }

    @Override
    public void approveCircleMember(Long circleId, Long memberId, Long approverId) {
        Circle circle = circleRepository.findById(circleId)
                .orElseThrow(() -> new IllegalArgumentException("써클이 존재하지 않습니다."));

        Member approver = memberRepository.findById(approverId)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

        if (!circle.getCreatorId().equals(approverId) && approver.getRole() != Role.ADMIN) {
            throw new SecurityException("승인 권한이 없습니다.");
        }

        CircleMember member = circle.getMembers().stream()
                .filter(cm -> cm.getMember().getId().equals(memberId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자는 써클에 가입하지 않았습니다."));

        member.setStatus(CircleMember.Status.APPROVED);
        circleMemberRepository.save(member);
    }
    @Override
    public List<CircleMember> getMembersInCircle(Long circleId, Long requesterId) {
        Circle circle = circleRepository.findById(circleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 써클입니다."));

        Member requester = memberRepository.findById(requesterId)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

        if (!circle.getCreatorId().equals(requesterId) && requester.getRole() != Role.ADMIN) {
            throw new SecurityException("열람 권한이 없습니다.");
        }

        return circle.getMembers().stream()
                .filter(cm -> cm.getStatus() == CircleMember.Status.APPROVED)
                .collect(Collectors.toList());
    }
    @Override
    public void rejectCircleMember(Long circleId, Long memberId, Long adminId) {
        Circle circle = circleRepository.findById(circleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 써클입니다."));

        Member admin = memberRepository.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 관리자입니다."));

        // 관리자 또는 써클 생성자만 거절 가능
        if (!circle.getCreatorId().equals(adminId) && admin.getRole() != Role.ADMIN) {
            throw new SecurityException("승인/거절 권한이 없습니다.");
        }

        CircleMember targetMember = circle.getMembers().stream()
                .filter(cm -> cm.getMember().getId().equals(memberId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버는 써클에 존재하지 않습니다."));

        targetMember.setStatus(CircleMember.Status.REJECTED);
        circleMemberRepository.save(targetMember);
    }
    @Override
    public void transferLeadership(Long circleId, Long currentLeaderId, Long newLeaderId) {
        Circle circle = circleRepository.findById(circleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 써클입니다."));

        Member currentLeader = memberRepository.findById(currentLeaderId)
                .orElseThrow(() -> new IllegalArgumentException("현재 사용자를 찾을 수 없습니다."));

        Member newLeader = memberRepository.findById(newLeaderId)
                .orElseThrow(() -> new IllegalArgumentException("새 리더를 찾을 수 없습니다."));

        if (!circle.getCreatorId().equals(currentLeaderId) && currentLeader.getRole() != Role.ADMIN) {
            throw new SecurityException("써클장 권한이 없습니다.");
        }


        CircleMember target = circle.getMembers().stream()
                .filter(cm -> cm.getMember().getId().equals(newLeaderId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버는 써클에 소속되어 있지 않습니다."));

        circle.setCreatorId(newLeaderId);
        target.setRole(CircleMember.Role.LEADER);

        circleRepository.save(circle);
        circleMemberRepository.save(target);
    }
    @Override
    public List<Circle> searchCircles(Boolean isPublic, String keyword) {
        if (isPublic != null && keyword != null && !keyword.isEmpty()) {
            return circleRepository.findByIsPublicAndTagsContaining(isPublic, keyword);
        } else if (isPublic != null) {
            return circleRepository.findByIsPublic(isPublic);
        } else if (keyword != null && !keyword.isEmpty()) {
            return circleRepository.findByTagsContaining(keyword);
        } else {
            return circleRepository.findAll();
        }
    }
    @Override
    public CircleDto getCircleDtoById(Long circleId) {
        Circle circle = circleRepository.findById(circleId)
                .orElseThrow(() -> new RuntimeException("해당 써클을 찾을 수 없습니다."));
        return new CircleDto(circle);
    }

    @Override
    public boolean canEditCircle(Long circleId, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));

        // 시스템 최고 관리자거나, 써클 리더인 경우에만 수정 가능
        return member.getRole() == Role.ADMIN ||
                circleMemberRepository.existsByCircleIdAndMemberIdAndRole(
                        circleId, memberId, CircleMember.Role.LEADER);
    }
    @Override
    public void leaveCircle(Long circleId, Long targetMemberId, Long requesterId) {
        Circle circle = circleRepository.findById(circleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 써클입니다."));

        Member requester = memberRepository.findById(requesterId)
                .orElseThrow(() -> new IllegalArgumentException("요청자가 존재하지 않습니다."));

        Member targetMember = memberRepository.findById(targetMemberId)
                .orElseThrow(() -> new IllegalArgumentException("대상 멤버가 존재하지 않습니다."));

        boolean isSelf = requesterId.equals(targetMemberId);
        boolean isAdmin = requester.getRole() == Role.ADMIN;
        boolean isLeader = circle.getCreatorId().equals(requesterId);

        if (!isSelf && !isAdmin && !isLeader) {
            throw new SecurityException("탈퇴시킬 권한이 없습니다.");
        }

        CircleMember circleMember = circle.getMembers().stream()
                .filter(cm -> cm.getMember().getId().equals(targetMemberId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("해당 멤버는 써클에 존재하지 않습니다."));

        circle.getMembers().remove(circleMember);
        targetMember.getCircleMembers().remove(circleMember);
        circleMemberRepository.delete(circleMember);
    }
    @Override
    public void requestJoinCircle(Long circleId, Long memberId) {
        Circle circle = circleRepository.findById(circleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 써클입니다."));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        // 비공개 써클인 경우 초대 여부 확인
        if (!circle.isPublic()) {
            if (!invitationService.hasValidInvitation(member, circle)) {
                throw new IllegalStateException("초대받지 않은 비공개 써클입니다.");
            }
        }

        // 중복 가입 방지 (이미 승인된 경우 재가입 불가)
        boolean alreadyApproved = circle.getMembers().stream()
                .anyMatch(cm ->
                        cm.getMember().getId().equals(memberId) &&
                                cm.getStatus() == CircleMember.Status.APPROVED
                );
        if (alreadyApproved) {
            throw new IllegalStateException("이미 승인된 멤버는 재가입할 수 없습니다.");
        }

        // 이미 요청한 상태면 중복 가입 요청 방지
        boolean alreadyRequested = circle.getMembers().stream()
                .anyMatch(cm ->
                        cm.getMember().getId().equals(memberId)
                );
        if (alreadyRequested) {
            throw new IllegalStateException("이미 가입 요청이 존재합니다.");
        }

        // 가입 요청 생성 (PENDING 상태)
        CircleMember cm = CircleMember.builder()
                .circle(circle)
                .member(member)
                .role(CircleMember.Role.MEMBER)
                .status(CircleMember.Status.PENDING)
                .commitRate(0.0)
                .skipCount(0)
                .points(0)
                .build();

        circleMemberRepository.save(cm);
        circle.getMembers().add(cm);
        member.getCircleMembers().add(cm);

        // 초대 사용 처리 (비공개 써클만)
        if (!circle.isPublic()) {
            invitationService.markInvitationUsed(member, circle);
        }
    }
    @Override
    public void requestJoinCircleByNickname(Long circleId, String memberNickname) {
        Circle circle = circleRepository.findById(circleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 써클입니다."));

        Member member = memberRepository.findByNickname(memberNickname)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        // 이미 가입했는지 확인
        boolean alreadyJoined = circle.getMembers().stream()
                .anyMatch(cm -> cm.getMember().getId().equals(member.getId()));
        if (alreadyJoined) {
            throw new IllegalStateException("이미 가입된 사용자입니다.");
        }

        // 비공개 써클이면 초대가 필요
        if (!circle.isPublic()) {
            if (!invitationService.hasValidInvitation(member, circle)) {
                throw new IllegalStateException("초대받지 않은 비공개 써클입니다.");
            }
        }

        // 써클 멤버로 등록 (가입 대기 상태)
        CircleMember circleMember = CircleMember.builder()
                .circle(circle)
                .member(member)
                .role(CircleMember.Role.MEMBER)
                .status(CircleMember.Status.PENDING) // 대기 상태로
                .commitRate(0.0)
                .skipCount(0)
                .points(0)
                .build();

        circle.getMembers().add(circleMember);
        member.getCircleMembers().add(circleMember);

        circleMemberRepository.save(circleMember);

        // 비공개 써클이면 초대 사용 처리
        if (!circle.isPublic()) {
            invitationService.markInvitationUsed(member, circle);
        }
    }














}





