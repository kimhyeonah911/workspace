package com.kh.jpa.service;

import com.kh.jpa.dto.BoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

public interface BoardService {
    Page<BoardDto.Response> getBoardList(Pageable pageable);
    BoardDto.Response getBoardDetail(Long boardNo);
    Long createBoard(BoardDto.Create createDto) throws IOException;
    BoardDto.Response updateBoard(Long boardNo, BoardDto.Update updateDto) throws IOException;
    void deleteBoard(Long boardNo);
    BoardDto.Response countBoard(Long boardNo);
}
