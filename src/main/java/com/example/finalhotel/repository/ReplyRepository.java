package com.example.finalhotel.repository;

import com.example.finalhotel.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply,Long> {


    public List<Reply>  findByBrand_BrandNum(Long target_num);
    public List<Reply>  findByBoard_BoardNum(Long target_num);
    public List<Reply>  findByHotel_HotelNum(Long target_num);
    



}
