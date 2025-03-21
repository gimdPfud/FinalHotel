package com.example.finalhotel.controller;
import com.example.finalhotel.dto.BoardDTO;
import com.example.finalhotel.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;
import java.util.List;
@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    //등록
    @GetMapping("/insert")
    public String insert (Model model, Long hotelNum) {
        log.info(hotelNum);
        model.addAttribute("hotelNum", hotelNum);
        log.info("insert into board");
        return "board/insert";
    }
    @PostMapping("/insert")
    public String insertPost(BoardDTO boardDTO, @RequestParam
            (name="hotelNum")Long hotelNum) {
        log.info("포스트:"+boardDTO);
        boardService.boardInsert(boardDTO, hotelNum);
        return "redirect:/board/list?hotelNum=" + hotelNum;
    }
    //목록
    @GetMapping("/list")
    public String List(Model model, Principal principal,
                       Long hotelNum) {
        List<BoardDTO> boardDTOList = boardService.boardList(hotelNum, principal);
        model.addAttribute("boardDTOList", boardDTOList);
        log.info("목록"+boardDTOList);
        return "board/list";
    }
    //읽기
    @GetMapping("/read")
    public String read(@RequestParam("BoardNum")
                           Model model, Long boardNum) {
        log.info("리드"+ boardNum);
        if (boardNum != null) {
            log.info("사용자 확인불가");
            return "redirect:/board/list";
        }
        BoardDTO boardDTO = boardService.boardRead(boardNum);
        log.info("사용자 확인"+boardDTO);
        model.addAttribute("boardDTO", boardDTO);

        return "board/read";
    }



    //수정



    //삭제
}

