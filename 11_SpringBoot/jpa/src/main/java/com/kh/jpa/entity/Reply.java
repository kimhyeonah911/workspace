package com.kh.jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "REPLY")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPLY_NO")
    private Long replyNo;

    @Column(name = "REPLY_CONTENT",  nullable = false, length = 400)
    private String replyContent;

    //어떤 게시글의 댓글인지 게시글 정보
    //댓글 : 게시글 (N : 1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REF_BNO")
    private Board board;

    //어떤 사람의 댓글인지 작성자 정보
    //댓글 : 작성자 (N : 1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REPLY_WRITER")
    private Member member;

    @CreationTimestamp
    @Column(name = "CREATE_DATE", nullable = false)
    private LocalDateTime createDate;

    @PrePersist
    public void prePersist() {
        this.createDate = LocalDateTime.now();
    }
}
