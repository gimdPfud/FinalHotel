package com.example.finalhotel.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class Reply {

    //pk
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyNum;

    //작성자
    private String replyName;

    //내용
    private String replyContent;


    //본사참조 테이블
    @ManyToOne
    @JoinColumn(name = "brandNum")
    private Brand brand;


    //호텔 참조 테이블
    @ManyToOne
    @JoinColumn(name = "hotelNum")
    private Hotel hotel;

    //게시판 참조 테이블
    @ManyToOne
    @JoinColumn(name ="boardNum" )
    private Board board;

}
