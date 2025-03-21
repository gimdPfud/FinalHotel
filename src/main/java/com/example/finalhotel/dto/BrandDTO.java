package com.example.finalhotel.dto;

import com.example.finalhotel.entity.Member;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BrandDTO {

    private Long brandNum;

    private String brandTitle;

    private String brandContent;

    private Member member; // 멤버참조
}
