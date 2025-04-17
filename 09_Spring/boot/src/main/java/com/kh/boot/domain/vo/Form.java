package com.kh.boot.domain.vo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Form {
    private int formNo;
    private String formTitle;
    private String formWriter;
    private String formResponseUrl;
    private String createDate;
    private String status;
    private String formDashBoardUrl;
}
