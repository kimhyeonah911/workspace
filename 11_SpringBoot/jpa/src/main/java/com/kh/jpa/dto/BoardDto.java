package com.kh.jpa.dto;

import com.kh.jpa.entity.Board;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public class BoardDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create{
        private String board_title;
        private String board_writer;
        private String board_content;
        private MultipartFile file;
        private List<String> tags;

        public Board toEntity() {
            return Board.builder()
                    .boardTitle(this.board_title)
                    .boardContent(this.board_content)
                    .build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response{
        private Long board_no;
        private String board_title;
        private String board_writer;
        private String board_content;
        private String origin_name;
        private String change_name;
        private Integer count;
        private LocalDateTime create_date;
        private String user_name;
        private List<String> tags;

        public static Response toSimpleDto(Board board) {
            return Response.builder()
                    .board_no(board.getBoardNo())
                    .board_title(board.getBoardTitle())
                    .board_writer(board.getMember().getUserId())
                    .origin_name(board.getOriginName())
                    .count(board.getCount())
                    .create_date(board.getCreateDate())
                    .build();
        }

        public static Response toDto(Board board) {
            return Response.builder()
                    .board_no(board.getBoardNo())
                    .board_title(board.getBoardTitle())
                    .board_writer(board.getMember().getUserId())
                    .board_content(board.getBoardContent())
                    .origin_name(board.getOriginName())
                    .change_name(board.getChangeName())
                    .count(board.getCount())
                    .create_date(board.getCreateDate())
                    .tags(board.getBoardTags().stream()
                            .map(boardTag -> boardTag.getTag().getTagName())
                            .toList())
                    .user_name(board.getMember().getUserName())
                    .build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update{
        private String board_title;
        private String board_content;
        private MultipartFile file;
        private List<String> tags;

        public Board toEntity() {
            return Board.builder()
                    .boardTitle(this.board_title)
                    .boardContent(this.board_content)
                    .build();
        }
    }
}
