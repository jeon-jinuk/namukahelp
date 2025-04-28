package com.routine.domain.b_circle.model;

import com.routine.domain.a_member.model.Member;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CircleInvitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "circle_id", nullable = false) // <-- 명시적으로 컬럼 이름과 not null 조건 지정
    private Circle circle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invited_member_id", nullable = false) // <-- 명시적으로
    private Member invitedMember;

    private boolean used = false;

    private LocalDateTime invitedAt = LocalDateTime.now();
}
