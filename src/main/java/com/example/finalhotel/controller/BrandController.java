package com.example.finalhotel.controller;

import com.example.finalhotel.dto.BrandDTO;
import com.example.finalhotel.service.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Log4j2
@Controller

public class BrandController {

    private final BrandService brandService;

    @GetMapping("/brand/insert")
    public String insertGet(BrandDTO brandDTO){
        log.info("get 방식 본사 가입 컨트롤러 진입" + brandDTO);

        return "brand/insert";
    }

    @GetMapping("/brand/insert")
    public String insertPost(BrandDTO brandDTO){
        log.info("post 방식 본사 가입 컨트롤러 진입" + brandDTO);

        brandDTO =
                breand

        return "brand/insert";
    }
}
