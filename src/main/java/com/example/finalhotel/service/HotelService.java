package com.example.finalhotel.service;

import com.example.finalhotel.dto.HotelDTO;
import com.example.finalhotel.entity.Hotel;

import java.util.List;

public interface HotelService {

    //호텔 등록
    public void hotelInsert(HotelDTO hotelDTO, Long brandNum);

    //호텔 목록
    public List<HotelDTO> hotelList(Long hotelNum);

    //호텔 상세보기
    public HotelDTO hotelRead(Long hotelNum);

    //호텔 수정

    //호텔 삭제




}
