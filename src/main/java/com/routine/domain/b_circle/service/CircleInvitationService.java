package com.routine.domain.b_circle.service;

import com.routine.domain.a_member.repository.MemberRepository;
import com.routine.domain.b_circle.model.Circle;
import com.routine.domain.a_member.model.Member;
import com.routine.domain.b_circle.model.CircleInvitation;
import com.routine.domain.b_circle.repository.CircleInvitationRepository;
import com.routine.domain.b_circle.repository.CircleMemberRepository;
import com.routine.domain.b_circle.repository.CircleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.routine.domain.b_circle.model.CircleMember;




import java.util.List;

@Service
@RequiredArgsConstructor
public class CircleInvitationService {

    private final CircleInvitationRepository invitationRepository;
    private final MemberRepository memberRepository;
    private final CircleRepository circleRepository;
    private final CircleMemberRepository circleMemberRepository;

    @Transactional
    public void invite(Member invitedMember, Circle circle) {
        CircleInvitation invitation = new CircleInvitation();
        invitation.setCircle(circle);
        invitation.setInvitedMember(invitedMember);
        invitation.setUsed(false);
        invitationRepository.save(invitation);
    }

    public boolean hasValidInvitation(Member member, Circle circle) {
        return invitationRepository.existsByCircleAndInvitedMemberAndUsedFalse(circle, member);
    }

    public void markInvitationUsed(Member member, Circle circle) {
        invitationRepository.findByCircleAndInvitedMemberAndUsedFalse(circle, member)
                .ifPresent(inv -> {
                    inv.setUsed(true);
                    invitationRepository.save(inv);
                });
    }
    public List<CircleInvitation> getPendingInvitations(Long memberId) {
        return invitationRepository.findByInvitedMemberIdAndUsedFalse(memberId);
    }
    @Transactional
    public void acceptInvitation(Long memberId, Long circleId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원 없음"));
        Circle circle = circleRepository.findById(circleId)
                .orElseThrow(() -> new IllegalArgumentException("써클 없음"));

        if (!hasValidInvitation(member, circle)) {
            throw new IllegalStateException("유효한 초대가 없습니다.");
        }

        if (circleMemberRepository.existsByCircleAndMember(circle, member)) {
            throw new IllegalStateException("이미 가입된 써클입니다.");
        }

        // 가입
        CircleMember newMember = CircleMember.builder()
                .circle(circle)
                .member(member)
                .role(CircleMember.Role.MEMBER)
                .status(CircleMember.Status.APPROVED)
                .build();
        circleMemberRepository.save(newMember);

        // 초대 사용 처리
        markInvitationUsed(member, circle);
    }
    @Transactional
    public void rejectInvitation(Long memberId, Long circleId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원 없음"));
        Circle circle = circleRepository.findById(circleId)
                .orElseThrow(() -> new IllegalArgumentException("써클 없음"));

        invitationRepository.findByCircleAndInvitedMemberAndUsedFalse(circle, member)
                .ifPresent(invitationRepository::delete); // 초대 삭제 (거절)
    }




}
