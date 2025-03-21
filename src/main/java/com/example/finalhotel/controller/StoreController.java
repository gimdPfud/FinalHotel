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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String delGet(Long storeNum, RedirectAttributes model){
        Long hotelNum = storeService.storeDel(storeNum);
        if(hotelNum==null){
            model.addAttribute("msg", "삭제할 수 없습니다.");
            return "redirect:/store/update?storeNum="+storeNum;
        }
        return "redirect:/store/list?hotelNum="+hotelNum;
    }

    /*나중에 수정*/
    @GetMapping("/update")
    public String updateGet(Long storeNum,Principal principal ,Model model){
        log.info(storeNum + "    " + principal);
        StoreDTO storeDTO = storeService.storeRead(storeNum);
        model.addAttribute("store", storeDTO);
        model.addAttribute("hotelDTOList",storeService.hotelList(principal.getName()));
        log.info(storeService.hotelList(principal.getName()));
        return "store/update";
    }
    @PostMapping("/update")
    public String updatePost(StoreDTO storeDTO){
        Long storeNum = storeService.storeUpdate(storeDTO);
        return "redirect:/store/read?storeNum="+storeNum;
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
