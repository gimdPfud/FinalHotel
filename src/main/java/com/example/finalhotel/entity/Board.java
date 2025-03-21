package com.example.finalhotel.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNum;
    private String boardTitle;
    private String boardContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberNum")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hoterNum")
    private Hotel hotel;
}
