package com.example.finalhotel.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreDTO {
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
    private Long hotelNum;
}
