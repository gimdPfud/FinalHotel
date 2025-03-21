package com.example.finalhotel.service;

import com.example.finalhotel.dto.ReplyDTO;
import com.example.finalhotel.entity.Board;
import com.example.finalhotel.entity.Brand;
import com.example.finalhotel.entity.Hotel;
import com.example.finalhotel.entity.Reply;
import com.example.finalhotel.repository.BoardRepositoty;
import com.example.finalhotel.repository.BrandRepository;
import com.example.finalhotel.repository.HotelRepository;
import com.example.finalhotel.repository.ReplyRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional

public class ReplyServiceImpl implements ReplyService{

    //리뷰 레퍼스토리
    private final ReplyRepository replyRepository;

    //Brand 레퍼지토리 가져오기
    private BrandRepository brandRepository;

    //Hotel 레퍼지토리 가져오기
    private HotelRepository hotelRepository;

    //Board 레퍼지토리 가져오기
    private BoardRepositoty boardRepositoty;



    private ModelMapper modelMapper = new ModelMapper();



    //브랜드(Brand) 리뷰등록 기능
    @Override
    public ReplyDTO insertReply(ReplyDTO replyDTO) {

        //========브랜드==========================
        Optional<Brand> optionalBrand =
        brandRepository.findById(replyDTO.getReplyNum());

        //브랜드 엔티티 값 예외처리
        Brand brand =
        optionalBrand.orElseThrow(EntityNotFoundException::new);
        log.info("브랜드에 있는 pk값 들어 왔니" + brand);
        //=================브랜드===엔티티 조립완료


        //========호텔 ================================
        Optional<Hotel> optionalHotel =
                hotelRepository.findById(replyDTO.getReplyNum());

        //예외처리
        Hotel hotel =
                optionalHotel.orElseThrow(EntityNotFoundException::new);
        //=================HOTEL===엔티티 조립완료


        //========보드 ================================
        Optional<Board> optionalBoard =
                boardRepositoty.findById(replyDTO.getReplyNum());

        //예외처리
        Board board =
                optionalBoard.orElseThrow(EntityNotFoundException::new);
        //=================Board===엔티티 조립완료




        //리뷰 엔티티 dto로 변환
        Reply reply =
                modelMapper.map(replyDTO, Reply.class);

        //브랜드, 호텔, 보드, 엔티티 set 해주기
        reply.setBrand(brand);
        reply.setHotel(hotel);
        reply.setBoard(board);

        //리뷰 Db 에 저장 메서드
        Reply brandresult =
                replyRepository.save(reply);

        //ReplyDTO에 반환 하기
        replyDTO = modelMapper.map(brandresult, ReplyDTO.class);


        return replyDTO;
    }


    //리뷰 목록 기능
    @Override
    public List<ReplyDTO> listReply() {

        //엔티티 배열 정리
        List<Reply> replyList =
        replyRepository.findAll();
        log.info("리뷰 엔티티 리스트 들어 왓니 " + replyList);
        log.info("없으면 null 이겠지 확인하자 " + replyList);

        //엔티티 값이 없으면 리터 처리
        if (replyList == null){

            return null;
        }

        //리뷰 DTO오 배열로 받아오기
        List<ReplyDTO> replyDTOList = new ArrayList<>();

        for (Reply reply : replyList) {

            ReplyDTO replyDTO = modelMapper.map(reply,ReplyDTO.class);
            log.info("리뷰 DTO로 변환 작업은 했니 했냐구 확인한다. ");

            replyDTOList.add(replyDTO);
        }


        return replyDTOList;
    }

    //3.리뷰 읽기 기능
    @Override
    public ReplyDTO readReply(Long replyNum) {

        Optional<Reply> optionalReply =
        replyRepository.findById(replyNum);

        Reply reply =
        optionalReply.orElseThrow(EntityNotFoundException::new);

        //DTO로 반환 해주기
        return modelMapper.map(reply,ReplyDTO.class);
    }

    //4.수정
    @Override
    public ReplyDTO updateReply(ReplyDTO replyDTO) {

        Optional<Reply> optionalReply =
        replyRepository.findById(replyDTO.getReplyNum());

        //예외처리

        Reply reply =
        optionalReply.orElseThrow(EntityNotFoundException::new);

        //작성자 수정
        reply.setReplyName(replyDTO.getReplyName());
        //내용 수정
        reply.setReplyContent(replyDTO.getReplyContent());


        return modelMapper.map(reply,ReplyDTO.class);
    }

    //5.리뷰 삭제
    @Override
    public void delReply(Long replyNum) {

        Optional<Reply> optionalReply =
        replyRepository.findById(replyNum);

        Reply reply =
        optionalReply.orElseThrow(EntityNotFoundException::new);

        //삭제 기능
        replyRepository.delete(reply);


    }


}
