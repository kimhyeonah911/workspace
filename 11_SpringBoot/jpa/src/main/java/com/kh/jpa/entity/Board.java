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
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Reply> replies = new ArrayList<>();

    //BoardTag : Board(N : 1)
    //orphanRemoval = true N : 1 또는 1 : N 연관관계에서 자식 생명주기를 부모가 완전히 통제하겠다.
    //부모 엔티티에서 자식과의 관계가 제거되면 자식도 자동으로 삭제
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<BoardTag> boardTags = new ArrayList<>();

    @Column(name = "BOARD_TITLE", length = 100, nullable = false)
    private String boardTitle;

    //Board : Member (N : 1)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_WRITER", nullable = false)
    private Member member;

    public void changeMember(Member member) {
        this.member = member;
        if(!member.getBoards().contains(this)){
            member.getBoards().add(this);
        } else{
            member.getBoards().remove(this);
        }
    }

    @Lob
    @Column(name = "BOARD_CONTENT", nullable = false)
    private String boardContent;

    @Column(name = "ORIGIN_NAME", length = 100)
    private String originName;

    @Column(name = "CHANGE_NAME", length = 100)
    private String changeName;

    public void changeFile(String originName, String changeName) {
        this.originName = originName;
        this.changeName = changeName;
    }

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

    public void changeTitle(String boardTitle){
        if(boardTitle != null && !boardTitle.isEmpty()){
            this.boardTitle = boardTitle;
        }
    }

    public void changeContent(String boardContent){
        if(boardContent != null && !boardContent.isEmpty()){
            this.boardContent = boardContent;
        }
    }

    public void boardUpdateCount(){
        this.count++;
    }
}
