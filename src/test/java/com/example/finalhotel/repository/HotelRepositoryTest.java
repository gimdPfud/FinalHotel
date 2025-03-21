package com.example.finalhotel.repository;

import com.example.finalhotel.entity.Brand;
import com.example.finalhotel.entity.Hotel;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.swing.plaf.basic.BasicRadioButtonMenuItemUI;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
@Transactional
@Rollback(value = false)
class HotelRepositoryTest {

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    BrandRepository brandRepository;

    @Test
    public void insertTest(){

        //부모 pk 찾아오기 (생성부터)
        Brand brand = new Brand();
        brand.setBrandTitle("본사 3");
        brand.setBrandContent("본사 3");     //pk 1인 데이터가 생기겠지

        brandRepository.save(brand);    //저장

        //pk 갖고와야됨
        Brand brand1 = brandRepository.findById(brand.getBrandNum()).orElseThrow(EntityNotFoundException::new);

        //호텔 객체 생성
        Hotel hotel = new Hotel();
        hotel.setHotelName("히히호텔");
        hotel.setHotelContent("히히히히");
        //부모 pk 넣어줘야 함
        hotel.setBrand(brand);

        Hotel result = hotelRepository.save(hotel);
        log.info("들어온 값 : " + result);

    }

//    @Test
//    public void findByBrand_BrandTitleTest(){
//
//        Hotel hotel = hotelRepository.findByBrand_BrandTitle("히히호텔");
//        log.info(hotel);    //null . .
//
//    }



//    @Test
//    public void listTest(){
//
//        List<Hotel> hotels = hotelRepository.findAll();
//        hotels.forEach(hotel -> log.info(hotel));
//
//    }
//
//    @Test
//    public void readTest(){
//
//        Optional<Hotel> hotel = hotelRepository.findById(2L).orElseThrow(EntityNotFoundException::new);
//
//    }


}