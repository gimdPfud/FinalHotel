package com.example.finalhotel.service;
import com.example.finalhotel.dto.BoardDTO;
import com.example.finalhotel.entity.Board;

import java.security.Principal;
import java.util.List;

public interface BoardService {
    //등록
    public void boardInsert(BoardDTO boardDTO,Long hotelNum);
    //목록
    public List<BoardDTO> boardList(Long hotelNum, Principal principal);
    //읽기
    public BoardDTO boardRead(Long boardNum);
    //수정
    public void boardUpdate(BoardDTO boardDTO);
    //삭제
    public void boardDelete(Long boardNum);




}
