package com.example.finalhotel.controller;

import com.example.finalhotel.dto.BrandDTO;
import com.example.finalhotel.repository.BrandRepository;
import com.example.finalhotel.service.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Log4j2
@Controller

public class BrandController {

    private final BrandService brandService;
    private final BrandRepository brandRepository;

    @GetMapping("/brand/insert")
    public String insertGet(BrandDTO brandDTO){
        log.info("get 방식 본사 가입 컨트롤러 진입" + brandDTO);

        return "brand/insert";
    }

    @PostMapping("/brand/insert")
    public String insertPost(BrandDTO brandDTO){
        log.info("post 방식 본사 가입 컨트롤러 진입" + brandDTO);

        brandDTO =
                brandService.insert(brandDTO);

        log.info("저장된 brandDTO" + brandDTO);

        return "brand/insert";
    }
}
