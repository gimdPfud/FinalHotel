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
    private  final BrandRepository brandRepository;

    //Hotel 레퍼지토리 가져오기
    private final HotelRepository hotelRepository;

    //Board 레퍼지토리 가져오기
    private final BoardRepositoty boardRepositoty;



    private ModelMapper modelMapper = new ModelMapper();


    //
    //1. 리뷰등록 기능
    @Override
    public ReplyDTO insertReply(String target, ReplyDTO replyDTO) {

        // 첫 번째로, ReplyDTO를 Reply 엔티티로 변환합니다.
        Reply reply = modelMapper.map(replyDTO, Reply.class);


        //String 벨류로 받는 메서드 입니다. Valeu="barnd" , Valeu="hotel" ,Valeu="board"
        // =================== 브랜드 처리 시작 ===================
        if (target.equals("brand")) {
            log.info("브랜드");  // "브랜드"라는 정보가 로깅됩니다.

            // 만약 Brand 번호가 없으면 예외를 던집니다.
            if (replyDTO.getBrandNum() == null) {
                throw new IllegalArgumentException("Brand PK is null");
            }

            // 데이터베이스에서 주어진 Brand 번호에 해당하는 Brand를 찾습니다.
            // 이때 커리문이 실행됩니다. 예시 커리문: `SELECT * FROM brand WHERE id = ?`
            Optional<Brand> optionalBrand = brandRepository.findById(replyDTO.getBrandNum());

            // 만약 찾은 결과가 없다면 예외를 던집니다.
            Brand brand = optionalBrand.orElseThrow(EntityNotFoundException::new);

            // 찾은 Brand를 reply 객체에 설정합니다.
            reply.setBrand(brand);
        }
        // =================== 호텔 처리 시작 ===================
        else if (target.equals("hotel")) {
            log.info("호텔");

            // 만약 Hotel 번호가 없으면 예외를 던집니다.
            if (replyDTO.getHotelNum() == null) {
                throw new IllegalArgumentException("Hotel PK is null");
            }

            // 데이터베이스에서 주어진 Hotel 번호에 해당하는 Hotel을 찾습니다.
            // 이때 커리문이 실행됩니다. 예시 커리문: `SELECT * FROM hotel WHERE id = ?`
            Optional<Hotel> optionalHotel = hotelRepository.findById(replyDTO.getHotelNum());

            // 만약 찾은 결과가 없다면 예외를 던집니다.
            Hotel hotel = optionalHotel.orElseThrow(EntityNotFoundException::new);

            // 찾은 Hotel을 reply 객체에 설정합니다.
            reply.setHotel(hotel);
        }
        // =================== 게시판 처리 시작 ===================
        else if (target.equals("board")) {
            log.info("보드");

            // 만약 Board 번호가 없으면 예외를 던집니다.
            if (replyDTO.getBoardNum() == null) {
                throw new IllegalArgumentException("Board PK is null");
            }

            // 데이터베이스에서 주어진 Board 번호에 해당하는 Board를 찾습니다.
            // 이때 커리문이 실행됩니다. 예시 커리문: `SELECT * FROM board WHERE id = ?`
            Optional<Board> optionalBoard = boardRepositoty.findById(replyDTO.getBoardNum());

            // 만약 찾은 결과가 없다면 예외를 던집니다.
            Board board = optionalBoard.orElseThrow(EntityNotFoundException::new);

            // 찾은 Board를 reply 객체에 설정합니다.
            reply.setBoard(board);
        }
        // =================== 잘못된 타겟 값 처리 ===================
        else {
            throw new IllegalArgumentException("Invalid target type: " + target);
        }

        // 리뷰를 데이터베이스에 저장합니다.
        // 이때 커리문이 실행됩니다. 예시 커리문: `INSERT INTO reply (...) VALUES (...)`
        Reply result = replyRepository.save(reply);

        // 저장된 reply 엔티티를 다시 ReplyDTO로 변환하여 반환합니다.
        replyDTO = modelMapper.map(result, ReplyDTO.class);

        return replyDTO;
    }


    //리뷰 목록 기능
    @Override
    public List<ReplyDTO> listReply(String target, Long targetNum) {

        //엔티티 배열 정리
        List<Reply> replyList = null;


        if(target.equals("hotel")){

            replyList = replyRepository.findByHotel_HotelNum(targetNum );
        }else if(target.equals("brand")){
            replyList = replyRepository.findByBrand_BrandNum(targetNum );
        }else{
            replyList = replyRepository.findByBoard_BoardNum(targetNum );

        }

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
