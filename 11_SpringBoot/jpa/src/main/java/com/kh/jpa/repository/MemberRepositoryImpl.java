package com.kh.jpa.repository;

import com.kh.jpa.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Transactional
@Repository //해당 클래스는 DB와 관련된 작업을 수행하는 클래스다.
public class MemberRepositoryImpl implements MemberRepository{

    @PersistenceContext //EntityManager 를 주입해준다.
    private EntityManager em;

    @Override
    public void save(Member member) {
        em.persist(member); //영속
    }

    @Override
    public Optional<Member> findOne(String userId) {
        return Optional.ofNullable(em.find(Member.class, userId));
    }

    @Override
    public void delete(Member member) {
        em.remove(member);
    }
}
