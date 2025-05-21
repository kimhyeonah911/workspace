package com.kh.jpa.dto;

import com.kh.jpa.entity.Notice;
import lombok.*;

import java.time.LocalDateTime;

public class NoticeDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create{
        private String notice_title;
        private String notice_writer;
        private String notice_content;

        public Notice toEntity(){
            return Notice.builder()
                    .noticeTitle(this.notice_title)
                    .noticeContent(this.notice_content)
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response{
        private Long notice_no;
        private String notice_title;
        private String notice_writer;
        private String notice_content;
        private LocalDateTime create_date;

        public static Response toDto(Notice notice){
            return Response.builder()
                    .notice_no(notice.getNoticeNo())
                    .notice_title(notice.getNoticeTitle())
                    .notice_writer(notice.getMember().getUserId())
                    .notice_content(notice.getNoticeContent())
                    .create_date(notice.getCreateDate())
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update {
        private String notice_title;
        private String notice_content;
    }
}
