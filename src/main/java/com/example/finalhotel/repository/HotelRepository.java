package com.example.finalhotel.repository;

import com.example.finalhotel.dto.HotelDTO;
import com.example.finalhotel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    //본사 이름(brandTitle)을 입력하면 본사의 pk를 찾아와서 Hotel에 넣어준다.
   public Hotel findByBrand_BrandTitle(String brandTitle);
    //+++ 이건 본사에 넣어야 하는 메서드인가?


}
