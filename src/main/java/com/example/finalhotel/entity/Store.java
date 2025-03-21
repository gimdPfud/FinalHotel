package com.example.finalhotel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_num")
    private Long storeNum;

    //상점이름
    private String storeName;

    //담당자이름
    private String storeOwnerName;

    //담당자연락처
    private String storeOwnerContact;

    //직영가맹여부
    private boolean isOwn;

    //호텔
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_num")
    private Hotel hotel;

}
