package com.routine.domain.a_member.dto;

import com.routine.domain.a_member.model.Member;
import lombok.Getter;

@Getter
public class MemberDto {
    private String username;
    private String name;
    private String nickname;
    private String email;
    private String phone;
    private Long id;



    public static MemberDto from(Member member) {
        MemberDto dto = new MemberDto();
        dto.id = member.getId();
        dto.username = member.getLoginId();
        dto.name = member.getName();
        dto.nickname = member.getNickname();
        dto.email = member.getEmail();
        dto.phone = member.getPhone();
        return dto;
    }


}
