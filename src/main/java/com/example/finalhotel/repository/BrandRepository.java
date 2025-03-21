package com.example.finalhotel.repository;

import com.example.finalhotel.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
Brand findByMember_MemberEmail(String Email);

}
