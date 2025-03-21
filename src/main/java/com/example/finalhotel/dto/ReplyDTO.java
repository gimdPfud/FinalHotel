package com.example.finalhotel.dto;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    //지점 참조 DTO

    //게시판 참조 DTO

}
