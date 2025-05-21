package com.kh.jpa.repository;

import com.kh.jpa.entity.Notice;

import java.util.List;
import java.util.Optional;

public interface NoticeRepository {
    void save(Notice notice);
    List<Notice> findAll();
    Optional<Notice> findOne(Long noticeId);
    List<Notice> findByTitle(String title);
    void delete(Notice notice);
}
