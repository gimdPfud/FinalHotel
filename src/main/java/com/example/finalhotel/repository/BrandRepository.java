package com.example.finalhotel.repository;

import com.example.finalhotel.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {

List<Brand> findByMember_MemberEmail(String Email);


}
