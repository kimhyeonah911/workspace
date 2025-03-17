package com.kh.boot.domain.vo;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Attachment {
    private int fileNo;
    private int refBno;
    private String originName;
    private String changeName;
    private Date uploadDate;
    private Integer fileLevel;
    private String status;
}
