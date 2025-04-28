package com.routine.dto;

import com.routine.domain.b_circle.model.Circle;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CircleDto {
    private Long id;
    private String name;
    private String description;
    private String tags;
    private boolean isPublic;


    public CircleDto(Circle circle) {
        this.id = circle.getId();
        this.name = circle.getName();
        this.description = circle.getDescription();
        this.tags = String.join(", ", circle.getTags());
        this.isPublic = circle.isPublic();
    }
}
