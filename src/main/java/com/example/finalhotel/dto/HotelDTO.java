package com.example.finalhotel.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class HotelDTO {

    private Long hotelNum;

    private String hotelName;

    private String hotelContent;
  
    private BrandDTO brandDTO;


}
