package com.example.finalhotel.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.management.relation.Role;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_num")
    private Long memberNum;
    private String memberEmail;
    private String memberName;
    private String memberPassword;
    private String role;
    private Boolean memberActivate;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "brandNum")
//    private Brand brand;
}
