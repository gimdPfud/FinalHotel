package com.example.finalhotel.repository;

import com.example.finalhotel.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepositoty extends JpaRepository<Board,Long> {
}
