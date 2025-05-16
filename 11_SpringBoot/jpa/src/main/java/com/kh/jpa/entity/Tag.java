package com.kh.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "TAG")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TAG_ID")
    private Long tagId;

    @OneToMany(mappedBy = "tag")
    private List<BoardTag> boardTags = new ArrayList<>();

    @Column(name = "TAG_NAME", nullable = false, unique = true,  length = 30)
    private String tagName;
}
