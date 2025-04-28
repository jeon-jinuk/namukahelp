package com.routine.domain.b_circle.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "circle_id")
    private Circle circle;

    private String title;
    private String content;

    private Long createdBy;

    private LocalDateTime createdAt;


    protected Notice() {}

    public Notice(Circle circle, String title, String content, Long createdBy) {
        this.circle = circle;
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
    }
}
