package com.kh.jpa.repository;

import com.kh.jpa.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    void save(Member member);
    List<Member> findAll();
    Optional<Member> findOne(String userId);
    List<Member> findByName(String name);
    void delete(Member member);
}
