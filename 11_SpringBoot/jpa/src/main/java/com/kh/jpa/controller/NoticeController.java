package com.kh.jpa.controller;

import com.kh.jpa.dto.NoticeDto;
import com.kh.jpa.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notices")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeService noticeService;

    @PostMapping
    public ResponseEntity<Long> addNotice(@RequestBody NoticeDto.Create createDto){
        Long noticeNo = noticeService.createNotice(createDto);
        return ResponseEntity.ok(noticeNo);
    }

    @GetMapping
    public ResponseEntity<List<NoticeDto.Response>> getAllNotices(){
        return ResponseEntity.ok(noticeService.findAllNotice());
    }

    @GetMapping("/{noticeNo}")
    public ResponseEntity<NoticeDto.Response> getNotice(@PathVariable Long noticeNo){
        return ResponseEntity.ok(noticeService.findNotice(noticeNo));
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<NoticeDto.Response>> searchNoticeByTitle(@RequestParam String title){
        return ResponseEntity.ok(noticeService.findByTitle(title));
    }

    @PutMapping("/{noticeNo}")
    public ResponseEntity<NoticeDto.Response> updateNotice(@PathVariable Long noticeNo, @RequestBody NoticeDto.Update updateDto){
        return ResponseEntity.ok(noticeService.updateNotice(noticeNo, updateDto));
    }

    @DeleteMapping("/{noticeNo}")
    public ResponseEntity<Void> deleteNotice(@PathVariable Long noticeNo){
        noticeService.deleteNotice(noticeNo);
        return ResponseEntity.ok().build();
    }
}
