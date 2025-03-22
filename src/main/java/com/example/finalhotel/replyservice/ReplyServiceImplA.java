package com.example.finalhotel.replyservice;

import com.example.finalhotel.dto.BoardDTO;
import com.example.finalhotel.dto.BrandDTO;
import com.example.finalhotel.dto.HotelDTO;


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

import java.util.List;

import static java.util.stream.StreamSupport.stream;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class ReplyServiceImplA implements ReplyServiceA {

    private final ReplyRepository replyRepository;
    private final BrandRepository brandRepository;
    private final HotelRepository hotelRepository;
    private final BoardRepositoty boardRepositoty;
    private final ModelMapper modelMapper = new ModelMapper();

    //람다식 표현
    //1.리뷰A 등록 메서드
    @Override
    public ReplyDTO insertReplyB(String target, ReplyDTO replyDTO) {

        Reply reply = modelMapper.map(replyDTO, Reply.class);

        //밑에 추가 메소드 있음  - ReplyDTO 에서
        Long targetNum = getTargetNum(target, replyDTO);

        //예외처리 하기 위에 받은 설정된 String 값 여부를 확인 하기
        if (targetNum == null) throw new IllegalArgumentException(target + " ID must not be null");

        //String value값이 들어 왔는지 여부 확인 - view 에서 String 으로 Value 값을 보내주면되ㅏㅁ
        //지금 설정은  Value="brand", Value="hotel", Value="board"

        reply = switch (target) {
            case "brand" -> { reply.setBrand(brandRepository.findById(targetNum).orElseThrow(EntityNotFoundException::new)); yield reply; }
            case "hotel" -> { reply.setHotel(hotelRepository.findById(targetNum).orElseThrow(EntityNotFoundException::new)); yield reply; }
            case "board" -> { reply.setBoard(boardRepositoty.findById(targetNum).orElseThrow(EntityNotFoundException::new)); yield reply; }
            default -> throw new IllegalArgumentException("서비스에서 들어온Target 값이 맞냐?: " + target);
        };
        return modelMapper.map(replyRepository.save(reply), ReplyDTO.class);
    }
    //2.리뷰 A 등록 메서드 getTargetNum
    //반환 타입이 Long 으로 반는 메서드 pk -- 타입이 Long 이기 때문에
    private Long getTargetNum(String target, ReplyDTO replyDTO) {

        //여기를 응용하면 타켓에 맞는 값이 들어오도록 응용이 가능함
        // target 값에 따라 DTO에서 적절한 값을 추출하여 반환합니다.
        switch (target) {
            case "brand":
                return replyDTO.getBrandNum();  // replyDTO에서 brandId를 가져옵니다. (brand에 해당하는 ID)
            case "hotel":
                return replyDTO.getHotelNum();  // replyDTO에서 hotelId를 가져옵니다. (hotel에 해당하는 ID)
            case "board":
                return replyDTO.getBoardNum();  // replyDTO에서 boardId를 가져옵니다. (board에 해당하는 ID)
            default:
                return null;  // 잘못된 target 값일 경우 null을 반환
        }
    }


    //일반 케이스문 확용
    @Override
    public ReplyDTO insertReplyC(String target, ReplyDTO replyDTO) {

        // 1. ReplyDTO를 Reply 객체로 수동 변환
        // ReplyDTO의 필드를 하나씩 Reply 객체에 설정
        Reply reply = new Reply();
        reply.setReplyNum(replyDTO.getReplyNum());
        reply.setReplyName(replyDTO.getReplyName());
        reply.setReplyContent(replyDTO.getReplyContent());

        // 2. target 값으로 targetNum을 가져옵니다.
        Long targetNum = getTargetNum(target, replyDTO);

        // 3. targetNum이 null인 경우 예외를 던집니다.
        if (targetNum == null) {
            throw new IllegalArgumentException(target + " ID must not be null");
        }

        // 4. target 값에 따라 적절한 엔티티 설정
        switch (target) {
            case "brand":
                // Brand ID로 brand를 찾아서 설정
                // 실행되는 SQL: `SELECT * FROM brand WHERE brand_num = ?`
                Brand brand = brandRepository.findById(targetNum)
                        .orElseThrow(() -> new EntityNotFoundException("Brand not found"));
                reply.setBrand(brand);
                break;

            case "hotel":
                // Hotel ID로 hotel을 찾아서 설정
                // 실행되는 SQL: `SELECT * FROM hotel WHERE hotel_num = ?`
                Hotel hotel = hotelRepository.findById(targetNum)
                        .orElseThrow(() -> new EntityNotFoundException("Hotel not found"));
                reply.setHotel(hotel);
                break;

            case "board":
                // Board ID로 board를 찾아서 설정
                // 실행되는 SQL: `SELECT * FROM board WHERE board_num = ?`
                Board board = boardRepositoty.findById(targetNum)
                        .orElseThrow(() -> new EntityNotFoundException("Board not found"));
                reply.setBoard(board);
                break;

            default:
                // target 값이 "brand", "hotel", "board"가 아닐 경우 예외 처리
                throw new IllegalArgumentException("서비스에서 들어온 Target 값이 맞냐?: " + target);
        }

        // reply 객체를 데이터베이스에 저장
        // 실행되는 SQL: `INSERT INTO reply
        // (reply_num, reply_name, reply_content, brand_id, hotel_id, board_id) VALUES (?, ?, ?, ?, ?, ?)`
        Reply result = replyRepository.save(reply);

        // 6. Reply 객체의 데이터를 DTO로 변환하여 반환

        ReplyDTO resultDTO = new ReplyDTO();
        resultDTO.setReplyNum(result.getReplyNum());
        resultDTO.setReplyName(result.getReplyName());
        resultDTO.setReplyContent(result.getReplyContent());
        //============여기 까지만 해도 반환 DTO로 반환 타입 끝 결과 값은  " resultDTO " 로 반화해도 됩 ======


        //요기서 부터는 DTO 예외 처리 인데 위에 DB 에서 예외 처리 했기 때문에 군이 밑에 코드는 필요가 없음
        // target이 brand, hotel, board에 해당하는 경우, 관련된 번호를 DTO에 세팅
        //6.1 ReplyDTO에서 가져온 필드 private Long hotelNum; , private Long boardNum; , private Long brandNum;
        if (result.getBrand() != null) {
            resultDTO.setBrandNum(result.getBrand().getBrandNum());  // 예시로 브랜드 필드만 가져옴
        }

        if (result.getHotel() != null) {
            resultDTO.setHotelNum(result.getHotel().getHotelNum());  // 예시로 호텔 필드만 가져옴
        }

        if (result.getBoard() != null) {
            resultDTO.setBoardNum(result.getBoard().getBoardNum());  // 예시로 게시판 필드만 가져옴
        }

        // 7. 완성된 ReplyDTO 반환
        return resultDTO;
    }




    //2.리뷰A 목록 메서드
    @Override
    public List<ReplyDTO> listReplyA(String target, Long targetNum) {
        if (targetNum == null) return List.of();

        return switch (target) {
            case "brand" -> replyRepository.findByBrand_BrandNum(targetNum)
                    .stream().map(r -> modelMapper.map(r, ReplyDTO.class)).toList();
            case "hotel" -> replyRepository.findByHotel_HotelNum(targetNum)
                    .stream().map(r -> modelMapper.map(r, ReplyDTO.class)).toList();
            default -> replyRepository.findByBoard_BoardNum(targetNum)
                    .stream().map(r -> modelMapper.map(r, ReplyDTO.class)).toList();
        };
    }

    //3.리뷰A 읽기 메서드
    @Override
    public ReplyDTO readReplyA(Long replyNum) {
        return replyRepository.findById(replyNum).map(r -> modelMapper.map(r, ReplyDTO.class)).orElseThrow(EntityNotFoundException::new);
    }


    //4.리뷰 A 등록 메서드
    @Override
    public ReplyDTO updateReplyA(ReplyDTO replyDTO) {
        return replyRepository.findById(replyDTO.getReplyNum()).map(reply -> {
            reply.setReplyName(replyDTO.getReplyName());
            reply.setReplyContent(replyDTO.getReplyContent());
            return modelMapper.map(reply, ReplyDTO.class);
        }).orElseThrow(EntityNotFoundException::new);
    }

    //5.리뷰A 삭제 메서드
    @Override
    public void delReplyA(Long replyNum) {
        replyRepository.findById(replyNum).ifPresentOrElse(
                replyRepository::delete, () -> { throw new EntityNotFoundException(); });
    }









}
