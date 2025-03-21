package com.example.finalhotel.service;
import com.example.finalhotel.dto.BoardDTO;
import com.example.finalhotel.entity.Board;
import com.example.finalhotel.entity.Hotel;
import com.example.finalhotel.repository.BoardRepositoty;
import com.example.finalhotel.repository.HotelRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepositoty boardRepositoty;
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    //등록
    @Override
    public void boardInsert(BoardDTO boardDTO, Long hotelNum) {
        Board board = modelMapper.map(boardDTO, Board.class);
        Hotel hotel = hotelRepository.findById(hotelNum).get();
        board.setHotel(hotel);
        board = boardRepositoty.save(board);
        log.info("Board added: " + boardDTO);
        boardRepositoty.save(board);
    }
    //목록
    @Override
    public List<BoardDTO> boardList(Long hotelNum, Principal principal) {
        String email = principal.getName();
        //hotelNum =
        List<Board>boardList=
                boardRepositoty.findByHotel_HotelNum(hotelNum);
        List<BoardDTO>boardDTOList=new ArrayList<>();
        for(Board board:boardList){
            BoardDTO boardDTO=modelMapper.map(board, BoardDTO.class);
            boardDTOList.add(boardDTO);
        }
        log.info("리스트: "+boardList);
        return boardDTOList;

    }
    //읽기
    @Override
    public BoardDTO boardRead(Long boardNum) {
        log.info("읽기 시작: " + boardNum);
        Optional<Board> optionalBoard=
                boardRepositoty.findById(boardNum);
        Board board = optionalBoard.get();
        BoardDTO boardDTO=modelMapper.map(board, BoardDTO.class);
        log.info("읽기 끝"+boardDTO);
        return boardDTO;
    }
    //수정
    @Override
    public void boardUpdate(BoardDTO boardDTO) {

    }
    //삭제
    @Override
    public void boardDelete(Long boardNum) {

    }
}
