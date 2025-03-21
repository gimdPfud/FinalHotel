package com.example.finalhotel.repository;

import com.example.finalhotel.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {
    public List<Store> findByHotelHotelNum(Long hotelNum);
    public List<Store> findByHotelBrandBrandNum(Long brandNum);
    public List<Store> findByHotelHotelNumAndIsOwn(Long hotelNum, String isOwn);
    public List<Store> findByHotelBrandBrandNumAndIsOwn(Long brandNum, String isOwn);
}
