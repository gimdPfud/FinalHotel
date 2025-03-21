package com.example.finalhotel.service;

import com.example.finalhotel.dto.BrandDTO;
import com.example.finalhotel.dto.MemberDTO;

import java.util.List;

public interface BrandService {

    // 등록
    public BrandDTO insert(BrandDTO brandDTO);

    // 목록
    public List<BrandDTO> brandDTOList(String email);

    // 읽기
    public BrandDTO read(Long num);


    // 수정
    public BrandDTO update(BrandDTO brandDTO);

    // 삭제
    public Long del(Long num);
}
