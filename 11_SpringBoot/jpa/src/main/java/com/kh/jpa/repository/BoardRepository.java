package com.kh.jpa.repository;

import com.kh.jpa.entity.Board;
import com.kh.jpa.enums.CommonEnums;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BoardRepository {
    PageImpl<Board> findByStatus(CommonEnums.Status status, Pageable pageable);
    Optional<Board> findById(Long boardNo);
    void save(Board board);
    void delete(Board board);
}
