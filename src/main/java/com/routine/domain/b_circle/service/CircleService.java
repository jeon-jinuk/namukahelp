package com.routine.domain.b_circle.service;

import com.routine.domain.b_circle.dto.CircleCreateRequestDto;
import java.util.List;
import com.routine.domain.b_circle.model.Circle;
import com.routine.domain.b_circle.model.CircleMember;
import com.routine.dto.CircleDto;

public interface CircleService {


    void createCircle(CircleCreateRequestDto dto, Long creatorId);
    void joinCircle(Long circleId, Long memberId);
    void updateCircle(Long circleId, Long memberId, CircleCreateRequestDto dto);
    void deleteCircle(Long circleId, Long memberId);
    void approveCircleMember(Long circleId, Long memberId, Long approverId);
    void rejectCircleMember(Long circleId, Long memberId, Long approverId);
    void transferLeadership(Long circleId, Long currentLeaderId, Long newLeaderId);
    void leaveCircle(Long circleId, Long targetMemberId, Long requesterId);
    void requestJoinCircle(Long circleId, Long memberId);
    void requestJoinCircleByNickname(Long circleId, String memberNickname);







    List<Circle> findAllCircles();

    List<Circle> findCirclesByMemberId(Long memberId);

    Circle findCircleById(Long circleId);

    List<CircleMember> getMembersInCircle(Long circleId, Long requesterId);

    List<Circle> searchCircles(Boolean isPublic, String keyword);

    CircleDto getCircleDtoById(Long circleId);

    boolean canEditCircle(Long circleId, Long memberId);







}
