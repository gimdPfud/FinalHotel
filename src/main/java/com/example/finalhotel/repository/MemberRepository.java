package com.example.finalhotel.repository;

import com.example.finalhotel.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
