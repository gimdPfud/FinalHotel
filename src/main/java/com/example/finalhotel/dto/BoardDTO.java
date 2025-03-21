package com.example.finalhotel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BoardDTO {
    private long boardNum;
    private String boardTitle;
    private String boardContent;
}
