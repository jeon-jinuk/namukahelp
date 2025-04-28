package com.routine.domain.a_member.service;

import com.routine.domain.a_member.dto.SignupRequestDto;
import com.routine.domain.a_member.model.Member;

import java.util.List;

public interface MemberService {
    void registerMember(SignupRequestDto dto);
    void updateMember(Long memberId, SignupRequestDto dto);
    void deleteMember(Long memberId);
    List<Member> findAllMembers();
}
