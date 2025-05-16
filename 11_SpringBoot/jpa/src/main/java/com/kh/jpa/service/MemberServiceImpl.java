package com.kh.jpa.service;

import com.kh.jpa.dto.MemberDto;
import com.kh.jpa.entity.Member;
import com.kh.jpa.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public String createMember(MemberDto.Create createDto) {
        //createDto : 사용자가 입력한 회원 정보
        Member member = createDto.toEntity(); //메모리 올라온 member
        memberRepository.save(member);
        return member.getUserId(); //영속상태의 member
    }

    @Transactional(readOnly = true)
    @Override
    public MemberDto.Response findMember(String userId) {
        return memberRepository.findOne(userId)
                        .map(MemberDto.Response::toDto)
                        .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

    @Override
    public MemberDto.Response updateMember(String userId, MemberDto.Update updateDto) {
        Member member = memberRepository.findOne(userId)
                        .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원입니다."));

        member.updateMemberInfo(
                updateDto.getUser_name(),
                updateDto.getEmail(),
                updateDto.getGender(),
                updateDto.getAge(),
                updateDto.getPhone(),
                updateDto.getAddress()
        );

        return MemberDto.Response.toDto(member);
    }

    @Override
    public void deleteMember(String userId) {
        Member member = memberRepository.findOne(userId)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 회원입니다."));

        memberRepository.delete(member);
    }
}
