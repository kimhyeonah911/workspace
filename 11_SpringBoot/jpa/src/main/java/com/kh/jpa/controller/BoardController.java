package com.kh.jpa.controller;

import com.kh.jpa.dto.BoardDto;
import com.kh.jpa.dto.PageResponse;
import com.kh.jpa.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    //page 보고자 하는 페이지 번호
    //size 몇 개씩 가지고 올 것인지
    //sort 정렬 기준 : 속성, 방향 (boardTitle, desc)
    @GetMapping
    public ResponseEntity<PageResponse<BoardDto.Response>> getBoards(@PageableDefault(size = 5, sort = "createDate", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(new PageResponse<>(boardService.getBoardList(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardDto.Response> getBoard(@PathVariable("id") Long boardNo){
        return ResponseEntity.ok(boardService.getBoardDetail(boardNo));
    }

    @PostMapping
    public ResponseEntity<Long> createBoard(@ModelAttribute BoardDto.Create createDto) throws IOException {
        return ResponseEntity.ok(boardService.createBoard(createDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardDto.Response> updateBoard(@PathVariable("id") Long boardNo,@ModelAttribute BoardDto.Update updateDto) throws IOException {
        return ResponseEntity.ok(boardService.updateBoard(boardNo, updateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable("id") Long boardNo){
        boardService.deleteBoard(boardNo);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/count")
    public ResponseEntity<BoardDto.Response> countBoard(@PathVariable("id") Long boardNo){
        return ResponseEntity.ok(boardService.countBoard(boardNo));
    }
}
