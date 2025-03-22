package com.example.finalhotel.service;

import com.example.finalhotel.dto.BoardDTO;
import com.example.finalhotel.dto.BrandDTO;
import com.example.finalhotel.dto.HotelDTO;
import com.example.finalhotel.dto.ReplyDTO;
import com.example.finalhotel.entity.Hotel;
import com.example.finalhotel.replyservice.ReplyServiceA;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
@Transactional
class ReplyServiceImplTest {

    @Autowired
    ReplyService replyService;

    //람다식 서비스 어노테이션
    @Autowired
    ReplyServiceA replyServiceA;


    //일반 리뷰 서비스 테스트용
    @Test
    @Rollback(value = false)
    public void insertTest(){
        //일반 리뷰 서비스 테스트용

        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setReplyContent("호텔");
        replyDTO.setReplyName("호텍");

        //반드시 DB 에 있는 더비 pk 값으로 테스트 하기 - 없으면 오류뜸 오류뜨면  DB 더미값 여부 확인하기
//        replyDTO.setBrandNum(1L);
        replyDTO.setHotelNum(3L);
//        replyDTO.setBoardNum(1L);


        replyDTO  =
                replyService.insertReply( "hotel", replyDTO);


        log.info(replyDTO);


        //예외처리
//        Optional<Hotel> optionalHotel =
//          hotelRepository.findById(replyDTO.getHotelNum());
//        Hotel hotel = optionalHotel.orElseThrow(() ->
//                new EntityNotFoundException("Hotel with ID " + replyDTO.getHotelNum() + " not found")
//        );
//        reply.setHotel(hotel);


    }

    //람다식
    @Test
    @Rollback(value = false)
    public void insertTestAAA() {
        //람다식으로 변환한  replyserviceA 테스트용

        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setReplyContent("람다식호텔");
        replyDTO.setReplyName("함다식 호텔");

        //반드시 DB 에 있는 더비 pk 값으로 테스트 하기 - 없으면 오류뜸 오류뜨면  DB 더미값 여부 확인하기
//        replyDTO.setBrandNum(1L);
        replyDTO.setHotelNum(3L);
//        replyDTO.setBoardNum(1L);


        replyDTO =
                replyServiceA.insertReplyC("hotel", replyDTO);


        log.info(replyDTO);
    }


    @Test
    @Rollback(value = false)
    public void insertTest2(){



        ReplyDTO replyDTO = new ReplyDTO();
        replyDTO.setReplyName("1이름이름호텔");
        replyDTO. setReplyContent("12호텔내용11");
        replyDTO.setHotelNum(1L);

        replyDTO =
                replyService.insertReply("hotel", replyDTO);


        System.out.println(replyDTO);


    }

}