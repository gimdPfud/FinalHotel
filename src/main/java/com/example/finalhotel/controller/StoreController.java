package com.example.finalhotel.controller;

import com.example.finalhotel.dto.StoreDTO;
import com.example.finalhotel.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {
    public final StoreService storeService;

    /*등록*/
    @GetMapping("/insert")
    public String insertGet(Long hotelNum, Model model){
        model.addAttribute("hotelNum",hotelNum);
        return "store/insert";
    }

    @GetMapping("/insertA")
    public String insertGetA(Principal principal, Model model){

        model.addAttribute("hotelDTOList",storeService.hotelList("sin@a.a"));
        return "store/insert";
    }

    @PostMapping("/insert")
    public String insertPost(StoreDTO storeDTO){
        Long storeNum = storeService.storeInsert(storeDTO);
        return "redirect:/store/read?storeNum="+storeNum;
    }

    /*읽기*/
    @GetMapping("/read")
    public String readGet(Long storeNum, Model model){
        StoreDTO storeDTO = storeService.storeRead(storeNum);
        model.addAttribute("storeDTO", storeDTO);
        return "store/read";
    }

    /*나중에 삭제*/
    @GetMapping("/del")
    public String delGet(){
        return null;
    }

    /*나중에 수정*/
    @GetMapping("/update")
    public String updateGet(){
        return null;
    }
    @PostMapping("/update")
    public String updatePost(){
        return null;
    }

    /*목록*/
    @GetMapping("/list")
    public String listGet(){
        return null;
    }
}
