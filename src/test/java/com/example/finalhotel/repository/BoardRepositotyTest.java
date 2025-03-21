package com.example.finalhotel.repository;
import com.example.finalhotel.dto.BoardDTO;
import com.example.finalhotel.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BoardRepositotyTest {
    @Autowired
    BoardRepositoty boardRepositoty;
    @Autowired
    BoardService boardService;
    @Test
    public void insertTest() {
        for (int i = 0; i < 10; i++) {
            BoardDTO boardDTO = new BoardDTO();
            boardDTO.setBoardTitle("제목테스트 .."+i);
            boardDTO.setBoardTitle("제목테스트 .." + i);
            //boardService.boardInsert(boardDTO);
        }
    }

}