package com.example.finalhotel.service;

import com.example.finalhotel.dto.BrandDTO;
import com.example.finalhotel.dto.MemberDTO;

public interface MemberService {

    void MemberSignup(MemberDTO memberDTO);
    void MemberDel(Long MemberNum);


}
