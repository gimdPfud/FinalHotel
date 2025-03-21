package com.example.finalhotel.service;

import com.example.finalhotel.dto.ReplyDTO;

import java.util.List;

public interface ReplyService {

    //브랜드,호텔,보드 리뷰등록기능
    public ReplyDTO insertReply(ReplyDTO replyDTO);




    //목록 - 리스트
    public List<ReplyDTO> listReply();

    //읽기
    public ReplyDTO readReply(Long replyNum);

    //수정
    public ReplyDTO updateReply(ReplyDTO replyDTO);

    //삭제
    public void delReply(Long replyNum);





}
