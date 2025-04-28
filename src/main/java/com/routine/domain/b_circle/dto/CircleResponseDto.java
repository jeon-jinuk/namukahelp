package com.routine.domain.b_circle.dto;

import com.routine.domain.b_circle.model.Circle;
import lombok.Getter;

@Getter
public class CircleResponseDto {
    private Long id;
    private String name;
    private String description;
    private String tags;
    private boolean isPublic;

    public CircleResponseDto(Circle circle) {
        this.id = circle.getId();
        this.name = circle.getName();
        this.description = circle.getDescription();
        this.tags = circle.getTags();
        this.isPublic = circle.isPublic();
    }
}
