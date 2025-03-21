package com.example.finalhotel.controller;

import com.example.finalhotel.dto.ReplyDTO;
import com.example.finalhotel.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/reply")
public class ReplyController {

    //리뷰 세비스 가져오기
    private final ReplyService replyService;

    @PostMapping("/insert")
    public String insertReplyPost(ReplyDTO replyDTO) {

        log.info("리뷰 리뷰 리뷰 리뷰 들록 post"+replyDTO);


        //본사
        replyService.insertReply(replyDTO);


        return "reply/insert";

    }






}
