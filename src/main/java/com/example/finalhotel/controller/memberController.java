package com.example.finalhotel.controller;

import com.example.finalhotel.dto.BrandDTO;
import com.example.finalhotel.dto.HotelDTO;
import com.example.finalhotel.dto.MemberDTO;
import com.example.finalhotel.dto.StoreDTO;
import com.example.finalhotel.entity.Brand;
import com.example.finalhotel.entity.Store;
import com.example.finalhotel.repository.BrandRepository;
import com.example.finalhotel.repository.HotelRepository;
import com.example.finalhotel.repository.MemberRepository;
import com.example.finalhotel.repository.StoreRepository;
import com.example.finalhotel.service.BrandService;
import com.example.finalhotel.service.HotelService;
import com.example.finalhotel.service.MemberService;
import com.example.finalhotel.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@ToString
@Log4j2
@Controller
public class memberController {

    private final MemberService memberService;
    private final BrandService brandService;
    private final StoreService storeService;
    private final HotelService hotelService;

    @GetMapping("/login")
    public String login() {
        return "member/login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        List<HotelDTO> hotelList = hotelService.getHotelFullList();
        model.addAttribute("hotelList", hotelList);
        List<StoreDTO> storeList = storeService.storeList();
        model.addAttribute("storeList", storeList);
        return "member/signup";
    }

    @PostMapping("/signup")
    public String register(MemberDTO memberDTO) {
        memberDTO.setMemberActivate(Boolean.FALSE);
        memberService.MemberSignup(memberDTO);
        return "redirect:/main";
    }

    @PostMapping("/memberdel")
    public String memberdel(Long memberdel) {
        memberService.MemberDel(memberdel);
        return "redirect:/login";
    }

    @GetMapping("/main")
    public String mainPage(Model model, Principal principal) {
        model.addAttribute("member", principal.getName());
        List<BrandDTO> brandList = brandService.brandDTOList(principal.getName());
        model.addAttribute("brandList", brandList);
        return "main/main";
    }

}

