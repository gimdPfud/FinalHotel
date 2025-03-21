package com.example.finalhotel.repository;

import com.example.finalhotel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    //본사(총판) pk 받으면 그 본사(총판)이 가지고있는 Hotel을 보여준다.
    public List<Hotel> findByBrand_BrandNum(Long brandNum);

    public List<Hotel> findByBrand_Member_MemberEmail(String email);

}
