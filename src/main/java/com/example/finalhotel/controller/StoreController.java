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
import java.util.List;

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
        model.addAttribute("hotelDTOList",storeService.hotelList(principal.getName()));
        return "store/insert";
    }

    @PostMapping("/insert")
    public String insertPost(StoreDTO storeDTO){
        log.info(storeDTO);
        Long storeNum = storeService.storeInsert(storeDTO);
        return "redirect:/store/read?storeNum="+storeNum;
    }

    /*읽기*/
    @GetMapping("/read")
    public String readGet(Long storeNum, Model model){
        StoreDTO storeDTO = storeService.storeRead(storeNum);
        String name = storeService.hotelName(storeDTO.getHotelNum());
        model.addAttribute("store", storeDTO);
        model.addAttribute("hotelName",name);
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
//    @GetMapping("/list")
//    public String listGet(Principal principal, Model model){
//        storeService.storeList()
//        return null;
//    }
    @GetMapping("/list")
    public String listGet(Long hotelNum, Model model){
        List<StoreDTO> storeDTOList = storeService.storeList(hotelNum, "hotel", "all");
        String name = storeService.hotelName(hotelNum);
        model.addAttribute("storeDTOList",storeDTOList);
        model.addAttribute("hotelName",name);
        return "store/list";
    }
}
