package com.kh.boot.domain.vo;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PictureBoard {
    private int pboardNo;
    private String pboardTitle;
    private String pboardWriter;
    private String changeName;
    private int count;
    private String createDate;
    private String status;

}
