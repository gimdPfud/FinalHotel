package com.example.finalhotel.service;

import com.example.finalhotel.dto.HotelDTO;
import com.example.finalhotel.entity.Hotel;

import java.util.List;

public interface HotelService {

    //호텔 등록
    public void hotelInsert(HotelDTO hotelDTO);

    //호텔 목록
    public List<HotelDTO> hotelList(Long hotelNum);

    //호텔 상세보기
    public HotelDTO hotelRead(Long hotelNum);

    //호텔 수정
    public void hotelUpdate(HotelDTO hotelDTO);


    //호텔 삭제
    public void hotelDel(Long hotelNum);


    //필요
    public List<HotelDTO> getHotelFullList();



}
