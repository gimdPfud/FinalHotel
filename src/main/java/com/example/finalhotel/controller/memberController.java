package com.example.finalhotel.controller;

import com.example.finalhotel.dto.BrandDTO;
import com.example.finalhotel.dto.MemberDTO;
import com.example.finalhotel.entity.Brand;
import com.example.finalhotel.repository.MemberRepository;
import com.example.finalhotel.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@ToString
@Log4j2
@Controller
public class memberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/login")
    public String login() {
        return "member/login";
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
    public String main(Model model, Principal principal) {
        model.addAttribute("member", principal.getName());
        List<Brand> brandList;
        model.addAttribute("brandList");
        return "main/main";
    }

}

