package com.example.finalhotel.dto;
import com.example.finalhotel.entity.Hotel;
import com.example.finalhotel.entity.Member;
import lombok.*;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long boardNum;
    private String boardTitle;
    private String boardContent;
    private Hotel hotel;
    private Member member;
}
