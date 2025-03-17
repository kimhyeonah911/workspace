package com.kh.boot.domain.vo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Board {
    @JsonProperty("board_no")
    private int boardNo;

    @JsonProperty("board_title")
    private String boardTitle;

    @JsonProperty("board_writer")
    private String boardWriter;

    @JsonIgnore
    private String boardContent;

    @JsonProperty("origin_name")
    private String originName;

    @JsonProperty("change_name")
    private String changeName;

    @JsonProperty("count")
    private int count;

    @JsonProperty("create_date")
    private Date createDate;

    @JsonIgnore
    private String status;
}
