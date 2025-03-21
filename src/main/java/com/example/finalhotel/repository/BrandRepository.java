package com.example.finalhotel.repository;

import com.example.finalhotel.entity.Brand;
import com.example.finalhotel.entity.Member;
import com.example.finalhotel.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {

public List<Brand> findByMember_MemberEmail(String email);



}
