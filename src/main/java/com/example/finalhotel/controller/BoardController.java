package com.example.finalhotel.controller;
import com.example.finalhotel.dto.BoardDTO;
import com.example.finalhotel.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    //등록
    @GetMapping("/inset")
    public String insert (Model model, Long hotelNum) {
        log.info(hotelNum);
        model.addAttribute("hotelNum", hotelNum);
        log.info("insert");
        return "board/insert";
    }
    @PostMapping("/insert")
    public String insertPost(BoardDTO boardDTO, @RequestParam
            (name="hotelNum")Long hotelNum) {
        log.info("Post"+boardDTO);
        boardService.boardInsert(boardDTO, hotelNum);
        return "redirect:/board/list?hotelNum=" + hotelNum;
    }
    //목록
    //읽기
    //수정
    //삭제

}
