package com.example.finalhotel.controller;

import com.example.finalhotel.dto.BrandDTO;
import com.example.finalhotel.repository.BrandRepository;
import com.example.finalhotel.service.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Controller

public class BrandController {

    private final BrandService brandService;
    private final BrandRepository brandRepository;

    @GetMapping("/brand/insert")
    public String insertGet(BrandDTO brandDTO) {
        log.info("get 방식 본사 가입 컨트롤러 진입" + brandDTO);

        return "brand/insert";
    }

    @PostMapping("/brand/insert")
    public String insertPost(BrandDTO brandDTO) {
        log.info("post 방식 본사 가입 컨트롤러 진입" + brandDTO);

        brandDTO =
                brandService.insert(brandDTO);

        log.info("저장된 brandDTO" + brandDTO);

        return "brand/insert";
    }

    @GetMapping("/brand/list")
    public String list(Model model) {
        log.info("본사 리스트 컨트롤러 진입");

        List<BrandDTO> brandDTOList =
                brandService.breandList();

        model.addAttribute("brandDTOList", brandDTOList);

        return "brand/list";
    }

    @GetMapping("/brand/read")
    public String read(Long brandNum, Model model) {
        log.info("본사 읽기 컨트롤러 진입" + brandNum);

        if (brandNum == null) {
            log.info("pk 넘버를 찾을 수 없습니다. 리스트 페이지로 갑니다.");
            return "redirect:/brand/list";
        }

        BrandDTO brandDTO =
                brandService.read(brandNum);

        log.info("저장된 pk 넘버를 찾아옵니다." + brandNum);

        model.addAttribute("brandDTO", brandDTO);

        return "brand/read";

    }

    @GetMapping("/update")
    public String getUpdate(Long brandNum, Model model) {
        log.info("본사 업데이트 get 컨트롤러 진입" + brandNum);

        if (brandNum == null) {
            log.info("본사의 넘버를 찾을 수 없습니다. 리스트 페이지로 꺼져");
            return "redirect:/brand/list";
        }

        BrandDTO brandDTO =
                brandService.read(brandNum);

        log.info("일단 pk값을 읽어와서 dto에 박아" + brandDTO);

        model.addAttribute("brandDTO", brandDTO);

        return "brand/update";

    }

    @PostMapping("/update")
    public String postUpdate(BrandDTO brandDTO){
        log.info("본사 업데이트 post 컨트롤러 진입");

        brandDTO =
                brandService.update(brandDTO);

        log.info("수정한 값 출력하기" + brandDTO);

        return "redirect:/brand/list";

    }

}
