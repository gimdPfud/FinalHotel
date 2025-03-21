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

    @Column(length = 50, nullable = false)
    private String brandTitle;

    @Column(length = 2000, nullable = false)
    private String brandContent;

    @ManyToOne
    @JoinColumn(name = "memberNum")
    private Member member;




}
