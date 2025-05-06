package com.routine.domain.a_member.service;

import com.routine.domain.a_member.dto.SignupRequestDto;
import com.routine.domain.a_member.model.Member;
import com.routine.domain.a_member.model.Role;
import com.routine.domain.a_member.repository.MemberRepository;
import com.routine.domain.a_member.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.routine.domain.a_member.dto.MemberDTO;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserInfoRepository userInfoRepository;


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

    @Transactional
    @Override
    public void updateMember(Long memberId, SignupRequestDto dto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원을 찾을 수 없습니다."));

        //member.setPassword(passwordEncoder.encode(dto.getPassword()));
        member.setName(dto.getName());
        member.setPhone(dto.getPhone());
        member.setNickname(dto.getNickname());
        member.setEmail(dto.getEmail());

        memberRepository.save(member);
    }





    @Transactional
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);  // 한 줄이면 끝남
    }






    @Override
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public MemberDTO findByLoginId(String loginId) {
        Member member = memberRepository.findByLoginId(loginId);
        if (member == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        return MemberDTO.from(member);
    }



}
