package com.kh.jpa.entity;

import com.kh.jpa.enums.CommonEnums;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "BOARD")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_NO")
    private Long boardNo;

    //Reply : Board(N : 1)
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Reply> replies = new ArrayList<>();

    //BoardTag : Board(N : 1)
    @OneToMany(mappedBy = "board")
    private List<BoardTag> boardTags = new ArrayList<>();

    @Column(name = "BOARD_TITLE", length = 100, nullable = false)
    private String boardTitle;

    //Board : Member (N : 1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_WRITER", nullable = false)
    private Member member;

    @Lob
    @Column(name = "BOARD_CONTENT", nullable = false)
    private String boardContent;

    @Column(name = "ORIGIN_NAME", length = 100)
    private String originName;

    @Column(name = "CHANGE_NAME", length = 100)
    private String changeName;

    private Integer count;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private CommonEnums.Status status;

    @PrePersist
    public void prePersist() {
        this.createDate = LocalDateTime.now();

        if(this.status == null) {
            this.status = CommonEnums.Status.Y;
        }

        this.count = 0;
    }
}
