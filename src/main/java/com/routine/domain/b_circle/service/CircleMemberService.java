package com.routine.domain.b_circle.service;

import com.routine.domain.a_member.repository.MemberRepository;
import com.routine.domain.b_circle.model.CircleMember;
import com.routine.domain.b_circle.repository.CircleMemberRepository;
import com.routine.domain.b_circle.repository.CircleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import com.routine.domain.a_member.model.Member;
import com.routine.domain.b_circle.model.Circle;
import com.routine.domain.b_circle.model.CircleMember;
import com.routine.domain.b_circle.repository.CircleMemberRepository;
import com.routine.domain.b_circle.repository.CircleRepository;
import com.routine.domain.a_member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CircleMemberService {

    private final CircleMemberRepository circleMemberRepository;
    private final CircleRepository circleRepository;
    private final MemberRepository memberRepository;

    // 특정 써클에 가입된 모든 멤버를 조회하는 메소드
    public List<CircleMember> getMembersByCircleId(Long circleId) {
        return circleMemberRepository.findByCircleId(circleId);
    }

    // 새로운 멤버 추가
    public CircleMember addMember(CircleMember member) {
        return circleMemberRepository.save(member);
    }

    // 멤버 삭제 메소드
    public void removeMember(Long memberId, Long circleId) {
        CircleMember member = circleMemberRepository.findByMemberIdAndCircleId(memberId, circleId)
                .orElseThrow(() -> new IllegalArgumentException("멤버 또는 써클이 존재하지 않습니다."));
        circleMemberRepository.delete(member);
    }
    @Transactional
    public void approveMember(Long circleId, Long memberId) {
        Circle circle = circleRepository.findById(circleId)
                .orElseThrow(() -> new IllegalArgumentException("써클 없음"));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원 없음"));

        CircleMember circleMember = circleMemberRepository.findByCircleAndMember(circle, member)
                .orElseThrow(() -> new IllegalArgumentException("가입 요청 없음"));

        circleMember.setStatus(CircleMember.Status.APPROVED);
        circleMemberRepository.save(circleMember);
    }


    @Transactional
    public void rejectMember(Long circleId, Long memberId) {
        Circle circle = circleRepository.findById(circleId)
                .orElseThrow(() -> new IllegalArgumentException("써클 없음"));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원 없음"));

        CircleMember circleMember = circleMemberRepository.findByCircleAndMember(circle, member)
                .orElseThrow(() -> new IllegalArgumentException("가입 요청 없음"));

        circleMember.setStatus(CircleMember.Status.REJECTED);
        circleMemberRepository.save(circleMember);
    }
}

