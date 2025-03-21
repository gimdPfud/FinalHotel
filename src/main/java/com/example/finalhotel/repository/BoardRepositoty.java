package com.example.finalhotel.repository;
import com.example.finalhotel.entity.Board;
import com.example.finalhotel.entity.Hotel;
import com.example.finalhotel.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepositoty extends JpaRepository<Board,Long> {

    List<Board> findByHotel_HotelNum(Long hotelNum);

}
