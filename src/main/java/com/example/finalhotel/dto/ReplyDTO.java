package com.example.finalhotel.dto;

import com.example.finalhotel.entity.Board;
import com.example.finalhotel.entity.Brand;
import com.example.finalhotel.entity.Hotel;
import jakarta.persistence.*;
import lombok.*;


@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {


    private Long replyNum;

    //작성자

private String replyName;

    //내용
    private String replyContent;


    //본사참조 DTO
    private BrandDTO brandDTO;


    //호텔 참조 DTO
    private HotelDTO hotelDTO;


    //게시판 참조 DTO
    private BoardDTO boardDTO;

    private Long hotelNum;
    private Long boardNum;
    private Long brandNum;

}
