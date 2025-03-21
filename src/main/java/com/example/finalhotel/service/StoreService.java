package com.example.finalhotel.service;

import com.example.finalhotel.dto.StoreDTO;

import java.util.List;

public interface StoreService {
    /*상점등록(상점DTO받아서)*/
    public Long brandInsert(StoreDTO storeDTO);

    /*상점수정(상점DTO받아서)*/
    public Long brandUpdate(StoreDTO storeDTO);

    /*상점삭제(상점pk받아서>호텔pk반환)*/
    public Long brandDel(Long storeNum);

    /*상점상세보기(상점pk받아서)*/
    public StoreDTO brandRead(Long storeNum);

    /*상점목록보기
    * 가맹 따로 직영 따로 */
    public List<StoreDTO> brandList(Long superNum, String hotelOrBrand, String isOwn);
    /*상점목록보기(브랜드와 협업하는 모든 가맹점 보기...(근데 이럴라면 가맹브랜드 테이블이 하나 더 있어야 편하겠다))*/
}
