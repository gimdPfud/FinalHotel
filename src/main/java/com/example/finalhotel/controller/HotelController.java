package com.example.finalhotel.controller;

import com.example.finalhotel.dto.HotelDTO;
import com.example.finalhotel.entity.Hotel;
import com.example.finalhotel.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/hotel")
@Log4j2
public class HotelController {

    private final HotelService hotelService;

    @GetMapping("/list")
    public String getList(Long brandNum, Model model) {
        log.info("list getList Controller 진입");
        List<HotelDTO> hotelDTOS = hotelService.hotelList(brandNum);
        model.addAttribute("hotelDTOS", hotelDTOS);

        return "hotel/list";
    }

    @GetMapping("/insert")
    public String getInsert(){
        log.info("list getInsert Controller 진입");

        return "hotel/insert";
    }

    @PostMapping("/insert")
    public String postInsert(HotelDTO hotelDTO){
        log.info("list postInsert Controller 진입");

        hotelService.hotelInsert(hotelDTO);

        return "redirect:/hotel/list";
    }






}
