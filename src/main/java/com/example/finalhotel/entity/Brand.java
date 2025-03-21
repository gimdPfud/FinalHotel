package com.example.finalhotel.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long brandNum;

    private String brandTitle;

    private String brandContent;

    @ManyToOne
    @JoinColumn(name = "memberNum")
    private Member member;




}
