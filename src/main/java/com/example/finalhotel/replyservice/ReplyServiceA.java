package com.example.finalhotel.replyservice;

import com.example.finalhotel.dto.ReplyDTO;

import java.util.List;

public interface ReplyServiceA {

    //브랜드,호텔,보드 리뷰등록기능
    public ReplyDTO insertReplyB(String target ,ReplyDTO replyDTO);

    ReplyDTO insertReplyC(String target, ReplyDTO replyDTO);




    //목록 - 리스트
    public List<ReplyDTO> listReplyA(String target, Long targetNum);

    //읽기
    public ReplyDTO readReplyA(Long replyNum);

    //수정
    public ReplyDTO updateReplyA(ReplyDTO replyDTO);

    //삭제
    public void delReplyA(Long replyNum);





}
