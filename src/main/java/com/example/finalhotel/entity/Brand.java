package com.example.finalhotel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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

    @NotEmpty(message = "제목은 필수입니다.")
    @Size(min = 2, max = 50, message = "제목은 최소 2글자 이상, 최대 50글자까지 입력하라고.")
    @Column(length = 50, nullable = false)
    private String brandTitle;

    @NotEmpty(message = "내용은 필수입니다.")
    @Size(min = 3, max = 2000, message = "내용은 최소 3글자 이상, 최대 2000글자까지 입력해")
    @Column(length = 2000, nullable = false)
    private String brandContent;

    @ManyToOne
    @JoinColumn(name = "memberNum")
    private Member member;




}
