package com.kh.jpa.service;

import com.kh.jpa.dto.NoticeDto;

import java.util.List;

public interface NoticeService {
    Long createNotice(NoticeDto.Create createDto);
    List<NoticeDto.Response> findAllNotice();
    NoticeDto.Response findNotice(Long noticeNo);
    List<NoticeDto.Response> findByTitle(String title);
    NoticeDto.Response updateNotice(Long noticeNo, NoticeDto.Update updateDto);
    void deleteNotice(Long noticeNo);
}
