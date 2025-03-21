package com.example.finalhotel.service;

import com.example.finalhotel.dto.BoardDTO;
import com.example.finalhotel.dto.BrandDTO;
import com.example.finalhotel.dto.HotelDTO;
import com.example.finalhotel.dto.ReplyDTO;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
@Transactional
class ReplyServiceImplTest {

    @Autowired
    ReplyService replyService;


    @Test
    @Rollback(value = false)
    public void insertTest(){
        //브랜드 1

        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setBrandNum(1L);


        Long a = 1L;
        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setReplyContent("안녕하세요");
        replyDTO.setReplyName("작성자");
        replyDTO.setBrandDTO(brandDTO);


        replyDTO  =
        replyService.insertReply( "brand", replyDTO);

        log.info(replyDTO);



    }

}