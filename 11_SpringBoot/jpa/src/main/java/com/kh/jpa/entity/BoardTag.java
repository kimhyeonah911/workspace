package com.kh.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "BOARD_TAG")
public class BoardTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_TAG_ID")
    private Long boardTagId;

    //게시글 : 중계테이블(1 : N)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_NO", nullable = false)
    private Board board;

    public void changeBoard(Board board) {
        this.board = board;
        if(!board.getBoardTags().contains(this)){
            board.getBoardTags().add(this);
        } else{
            board.getBoardTags().remove(this);
        }
    }

    //태그 : 중계테이블(1 : N)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TAG_ID", nullable = false)
    private Tag tag;
}
