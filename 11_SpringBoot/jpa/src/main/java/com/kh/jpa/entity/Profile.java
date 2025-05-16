package com.kh.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "PROFILE")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROFILE_ID")
    private Long profileId;

    //회원 : 프로필 (1 : 1)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", unique = true)
    private Member member;

    @Column(name = "PROFILE_IMAGE", length = 100)
    private String profileImage;

    @Column(length = 300)
    private String intro;
}
