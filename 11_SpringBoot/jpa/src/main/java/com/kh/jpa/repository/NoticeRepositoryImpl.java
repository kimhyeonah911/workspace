package com.kh.jpa.repository;

import com.kh.jpa.entity.Notice;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class NoticeRepositoryImpl implements NoticeRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Notice notice) {
        em.persist(notice);
    }

    @Override
    public List<Notice> findAll() {
        return em.createQuery("select n from Notice n", Notice.class).getResultList();
    }

    @Override
    public Optional<Notice> findOne(Long noticeId) {
        return Optional.ofNullable(em.find(Notice.class, noticeId));
    }

    @Override
    public List<Notice> findByTitle(String title) {
        String query = "select n from Notice n where n.noticeTitle LIKE :title";
        return em.createQuery(query, Notice.class)
                .setParameter("title", "%" + title + "%").getResultList();
    }

    @Override
    public void delete(Notice notice) {
        em.remove(notice);
    }
}
