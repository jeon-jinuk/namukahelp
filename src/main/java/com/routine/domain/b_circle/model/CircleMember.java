package com.routine.domain.b_circle.model;

import com.routine.domain.a_member.model.Member;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CircleMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "circle_id")
    @JsonIgnore
    private Circle circle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    private double commitRate;
    private int skipCount;
    private int points;

    public enum Role {
        LEADER,
        MEMBER,
        ADMIN
    }

    public enum Status {
        PENDING,
        APPROVED,
        REJECTED
    }
}
