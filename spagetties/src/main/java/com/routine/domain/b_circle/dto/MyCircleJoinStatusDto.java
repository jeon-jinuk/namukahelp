package com.routine.domain.b_circle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyCircleJoinStatusDto {
    private Long circleId;
    private String circleName;
    private String status;
}
