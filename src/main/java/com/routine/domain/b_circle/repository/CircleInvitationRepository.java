package com.routine.domain.b_circle.repository;

import com.routine.domain.b_circle.model.CircleInvitation;
import com.routine.domain.b_circle.model.Circle;
import com.routine.domain.a_member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CircleInvitationRepository extends JpaRepository<CircleInvitation, Long> {
    boolean existsByCircleAndInvitedMemberAndUsedFalse(Circle circle, Member invitedMember);

    Optional<CircleInvitation> findByCircleAndInvitedMemberAndUsedFalse(Circle circle, Member invitedMember);
    List<CircleInvitation> findByInvitedMemberIdAndUsedFalse(Long memberId);
}
