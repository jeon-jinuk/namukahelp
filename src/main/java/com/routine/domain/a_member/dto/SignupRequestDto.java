package com.routine.domain.a_member.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    private String loginId;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String nickname;
}
