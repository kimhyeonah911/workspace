package com.kh.jpa.service;

import com.kh.jpa.dto.MemberDto;

import java.util.List;

public interface MemberService {
    String createMember(MemberDto.Create createDto);
    List<MemberDto.Response> findAllMember();
    MemberDto.Response findMember(String userId);
    List<MemberDto.Response> findByName(String name);
    MemberDto.Response updateMember(String userId, MemberDto.Update updateDto);
    void deleteMember(String userId);
}
