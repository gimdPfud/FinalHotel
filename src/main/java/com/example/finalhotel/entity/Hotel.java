package com.example.finalhotel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_num")
    private Long hotelNum;

    //호텔명
    private String hotelName;

    //호텔 내용
    private String hotelContent;

    //참조(본사를)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_num")
    private Brand brand;


}
