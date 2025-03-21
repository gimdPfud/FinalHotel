package com.example.finalhotel.service;

import com.example.finalhotel.dto.BrandDTO;
import com.example.finalhotel.dto.MemberDTO;
import com.example.finalhotel.entity.Brand;
import com.example.finalhotel.entity.Member;
import com.example.finalhotel.repository.BrandRepository;
import com.example.finalhotel.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper = new ModelMapper();
    private final MemberRepository memberRepository;
    private final BrandRepository brandRepository;

    public void MemberSignup(MemberDTO memberDTO) {
        Member member = modelMapper.map(memberDTO, Member.class);
        member.setMemberPassword(passwordEncoder.encode(member.getMemberPassword()));
        memberRepository.save(member);
    }

    public void MemberDel(Long MemberNum){
        memberRepository.deleteById(MemberNum);
    }
}