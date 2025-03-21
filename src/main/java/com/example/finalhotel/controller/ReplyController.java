package com.example.finalhotel.controller;

import com.example.finalhotel.dto.ReplyDTO;
import com.example.finalhotel.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/reply")
public class ReplyController {

    //리뷰 세비스 가져오기
    private final ReplyService replyService;


    @GetMapping("/insert")
    public String inser(Model model){

        model.addAttribute("replyDTOList", replyService.listReply("board", 1L));
        return "reply/insert";
    }

    @PostMapping("/insert")
    public String insertReplyPost(ReplyDTO replyDTO , String target) {

        log.info("리뷰 리뷰 리뷰 리뷰 들록 post"+replyDTO);
        log.info("리뷰 리뷰 타켓은?? " +target);





        //등록

        replyService.insertReply(target, replyDTO);

        if(target.equals("hotel")){
            return "redirect:/hotel/read/" + replyDTO.getHotelNum();
        }else    if(target.equals("brand")){
            return "redirect:/brand/read?brandNum=" + replyDTO.getBrandNum();
        }else  {
            return "redirect:/board/read?num=" + replyDTO.getBoardNum();
        }




    }

    @PostMapping("/insertR")
    public ResponseEntity insertReplyPostRest(ReplyDTO replyDTO , String target) {

        log.info("리뷰 리뷰 리뷰 리뷰 들록 post"+replyDTO);
        log.info("리뷰 리뷰 타켓은?? " +target);

        //등록

        replyService.insertReply(target, replyDTO);


        return new ResponseEntity<String>("저장성공", HttpStatus.OK);

    }

    @GetMapping("/list")
    public ResponseEntity listReplyGet( String target , Long targetNum ){
        log.info("리스트 페이지가 들어 왔니 ");

        List<ReplyDTO> replyDTOList =
                replyService.listReply(target, targetNum);

        return new ResponseEntity< List<ReplyDTO>>(replyDTOList, HttpStatus.OK);
    }







}
