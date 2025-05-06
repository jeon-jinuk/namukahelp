package com.routine.domain.b_circle.repository;

import com.routine.domain.a_member.model.Member;
import com.routine.domain.b_circle.model.Circle;
import com.routine.domain.b_circle.model.CircleMember;
import com.routine.domain.b_circle.model.CircleMember.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CircleMemberRepository extends JpaRepository<CircleMember, Long> {


    List<CircleMember> findByCircleId(Long circleId);

    Optional<CircleMember> findByMemberIdAndCircleId(Long memberId, Long circleId);

    List<CircleMember> findByMemberId(Long memberId);

    boolean existsByCircleIdAndMemberId(Long circleId, Long memberId);

    List<CircleMember> findByCircleIdAndRole(Long circleId, Role role);

    List<CircleMember> findByMemberIdAndRole(Long memberId, Role role);

    long countByCircleId(Long circleId);

    boolean existsByCircleIdAndMemberIdAndRole(Long circleId, Long memberId, CircleMember.Role role);

    boolean existsByCircleAndMember(Circle circle, Member member);

    Optional<CircleMember> findByCircleAndMember(Circle circle, Member member);

    List<CircleMember> findByMemberIdOrderByCreatedAtDesc(Long memberId);




}
