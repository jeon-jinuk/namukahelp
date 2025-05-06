package com.routine.domain.b_circle.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CircleCreateRequestDto {
    private String name;
    private String description;
    private String tags;
    private boolean isPublic = true;
    private boolean editable;

}
