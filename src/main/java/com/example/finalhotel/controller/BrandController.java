package com.example.finalhotel.controller;

import com.example.finalhotel.dto.BrandDTO;
import com.example.finalhotel.dto.MemberDTO;
import com.example.finalhotel.dto.ReplyDTO;
import com.example.finalhotel.repository.BrandRepository;
import com.example.finalhotel.repository.ReplyRepository;
import com.example.finalhotel.service.BrandService;
import com.example.finalhotel.service.ReplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Log4j2
@Controller
@ToString

public class BrandController {

    private final BrandService brandService;
    private final BrandRepository brandRepository;
    private final ReplyService replyService;

    @GetMapping("/brand/insert")
    public String insertGet(BrandDTO brandDTO, String email, Model model) {
        log.info("get 방식 본사 가입 컨트롤러 진입" + brandDTO);

        model.addAttribute("email", email);

        return "brand/insert";
    }

    @PostMapping("/brand/insert")
    public String insertPost(@Valid BrandDTO brandDTO, BindingResult bindingResult) {
        log.info("post 방식 본사 가입 컨트롤러 진입" + brandDTO);

        if (bindingResult.hasErrors()) {

            log.info("유효성검사 에러~~~");
            log.info(bindingResult.getAllErrors());
            log.info("값 찾을 수 없으니까 등록페이지로 다시가라.");

            return "brand/insert";

        }

        brandDTO =
                brandService.insert(brandDTO);

        log.info("저장된 brandDTO" + brandDTO);

        return "redirect:/brand/list";
    }

    @GetMapping("/brand/list")
    public String list(Model model, String email) {
        log.info("본사 리스트 컨트롤러 진입");

        List<BrandDTO> brandDTOList =
                brandService.brandDTOList(email);

        model.addAttribute("brandDTOList", brandDTOList);
        model.addAttribute("email", email);

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

        List<ReplyDTO> replyDTOList =
                replyService.listReply("brand", brandNum);


        log.info("저장된 pk 넘버를 찾아옵니다." + brandNum);

        model.addAttribute("brandDTO", brandDTO);
        model.addAttribute("replyDTOList", replyDTOList);

        return "brand/read";

    }

    @GetMapping("/brand/update")
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

    @PostMapping("/brand/update")
    public String postUpdate(BrandDTO brandDTO){
        log.info("본사 업데이트 post 컨트롤러 진입" + brandDTO);

        brandDTO =
                brandService.update(brandDTO);

        log.info("수정한 값 출력하기" + brandDTO);

        return "redirect:/brand/list";

    }

    @PostMapping("/brand/del")
    public String postDel(BrandDTO brandDTO, Long brandNum){
        log.info("본사 삭제 포스트 진입");
        log.info("삭제할 번호를 찾기" + brandNum);

        brandService.del(brandDTO.getBrandNum());

        if (brandNum == null){
            log.info("pk 넘버값을 찾을 수 없습니다. 다시 한번 확인하세요");
        }

        log.info("삭제가 완료되었습니다. 리스트 페이지로 이동합니다.");

        return "redirect:/brand/list";
    }

}
