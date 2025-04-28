package com.routine.domain.a_member.service;

import com.routine.domain.a_member.dto.SignupRequestDto;
import com.routine.domain.a_member.model.Member;
import com.routine.domain.a_member.model.Role;
import com.routine.domain.a_member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerMember(SignupRequestDto dto) {
        if (memberRepository.findByLoginId(dto.getLoginId()) != null) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }

        Member member = Member.builder()
                .loginId(dto.getLoginId())
                .nickname(dto.getNickname())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .phone(dto.getPhone())
                .role(Role.USER)
                .build();

        memberRepository.save(member);
    }

    @Override
    public void updateMember(Long memberId, SignupRequestDto dto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원을 찾을 수 없습니다."));

        member.setPassword(passwordEncoder.encode(dto.getPassword()));
        member.setName(dto.getName());
        member.setPhone(dto.getPhone());
        member.setNickname(dto.getNickname());

        memberRepository.save(member);
    }

    @Override
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원을 찾을 수 없습니다."));

        memberRepository.delete(member);
    }

    @Override
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }
}
