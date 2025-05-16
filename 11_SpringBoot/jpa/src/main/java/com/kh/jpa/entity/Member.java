package com.kh.jpa.entity;

import com.kh.jpa.enums.CommonEnums;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) //JPA 스펙상 필수 + 외부 생성 방지
@AllArgsConstructor
@Builder
@Getter
@Table(name = "MEMBER")
@DynamicInsert //insert 할 때 null 이 아닌 필드만 쿼리에 포함, default 값 활용
@DynamicUpdate //변경된 필드만 update 문에 포함
public class Member {
    @Id
    @Column(name = "USER_ID", length = 30)
    private String userId;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private Profile profiles;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Notice> notices = new ArrayList<>();

    //1 : N 연관관계 주인 = Board
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Reply> replies = new ArrayList<>();

    @Column(name = "USER_PWD", length = 100, nullable = false)
    private String userPwd;

    @Column(name = "USER_NAME", length = 15, nullable = false)
    private String userName;

    @Column(name = "EMAIL",length = 254)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private Gender gender;

    private Integer age;

    @Column(length = 13)
    private String phone;

    @Column(length = 100)
    private String address;

    @Column(name = "ENROLL_DATE")
    private LocalDateTime enrollDate;

    @Column(name = "MODIFY_DATE")
    private LocalDateTime modifyDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private CommonEnums.Status status;

    public enum Gender {
        M, F
    }

    public enum Status {
        Y, N
    }

    //엔티티가 영속성 컨텍스트에 저장되기 전(em.persist())에 실행되는 메서드
    //초기설정을 해두는 용도로 사용
    @PrePersist
    public void prePersist() {
        this.enrollDate = LocalDateTime.now();
        this.modifyDate = LocalDateTime.now();

        if(this.status == null) {
            this.status = CommonEnums.Status.Y;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.modifyDate = LocalDateTime.now();
    }

    public void updateMemberInfo(String userName, String email, Gender gender, Integer age, String phone, String address) {
        this.userName = userName;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.address = address;

    }
}
