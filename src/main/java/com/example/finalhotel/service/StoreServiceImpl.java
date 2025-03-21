package com.example.finalhotel.service;

import com.example.finalhotel.dto.HotelDTO;
import com.example.finalhotel.dto.StoreDTO;
import com.example.finalhotel.entity.Store;
import com.example.finalhotel.repository.HotelRepository;
import com.example.finalhotel.repository.StoreRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class StoreServiceImpl implements StoreService{
    private final StoreRepository storeRepository;
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Long storeInsert(StoreDTO storeDTO) {
        Store store = modelMapper.map(storeDTO, Store.class);
        store = storeRepository.save(store);
        return store.getStoreNum();
    }

    @Override
    public List<HotelDTO> hotelList(String email) {
//        return hotelRepository.findAll().stream().map( hotel->modelMapper.map(hotel,HotelDTO.class)).collect(Collectors.toList());
        return hotelRepository.findByBrand_Member_MemberEmail(email).stream().map( hotel->modelMapper.map(hotel,HotelDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Long storeUpdate(StoreDTO storeDTO) {
        Store store = storeRepository.findById(storeDTO.getStoreNum()).orElseThrow(EntityNotFoundException::new);
        store.setStoreName(storeDTO.getStoreName());
        store.setStoreOwnerName(storeDTO.getStoreOwnerName());
        store.setStoreOwnerContact(storeDTO.getStoreOwnerContact());
        store.setIsOwn(storeDTO.getIsOwn());
        return store.getStoreNum();
    }

    @Override
    public Long storeDel(Long storeNum) {
        Store store = storeRepository.findById(storeNum).orElseThrow(EntityNotFoundException::new);
        if(store!=null){
            Long hotelNum = store.getHotel().getHotelNum();
            storeRepository.deleteById(storeNum);
            return hotelNum;
        }return null;
    }

    @Override
    public StoreDTO storeRead(Long storeNum) {
        Store store = storeRepository.findById(storeNum).orElseThrow(EntityNotFoundException::new);
        StoreDTO storeDTO = modelMapper.map(store, StoreDTO.class);
        return storeDTO;
    }

    @Override
    public List<StoreDTO> storeList(Long superNum, String hotelOrBrand, String isOwn) {
        List<StoreDTO> storeDTOList = new ArrayList<>();
        if(hotelOrBrand.equals("hotel")){
            if(isOwn.equals("Y")){
                List<Store> storeList = storeRepository.findByHotelHotelNumAndIsOwn(superNum, isOwn);
                storeList.forEach(
                        store ->
                                storeDTOList.add(
                                        modelMapper.map(store, StoreDTO.class))
                );
            } else if (isOwn.equals("N")) {
                List<Store> storeList = storeRepository.findByHotelHotelNumAndIsOwn(superNum, isOwn);
                storeList.forEach(
                        store ->
                                storeDTOList.add(
                                        modelMapper.map(store, StoreDTO.class))
                );
            } else {
                List<Store> storeList = storeRepository.findByHotelHotelNum(superNum);
                storeList.forEach(store -> storeDTOList.add(modelMapper.map(store, StoreDTO.class)));
            }
            return storeDTOList;
        }
        else if (hotelOrBrand.equals("brand")) {
            if(isOwn.equals("Y")){
                List<Store> storeList = storeRepository.findByHotelBrandBrandNumAndIsOwn(superNum, isOwn);
                storeList.forEach(
                        store ->
                                storeDTOList.add(
                                        modelMapper.map(store, StoreDTO.class))
                );
            } else if (isOwn.equals("N")) {
                List<Store> storeList = storeRepository.findByHotelBrandBrandNumAndIsOwn(superNum, isOwn);
                storeList.forEach(
                        store ->
                                storeDTOList.add(
                                        modelMapper.map(store, StoreDTO.class))
                );
            } else{
                List<Store> storeList = storeRepository.findByHotelBrandBrandNum(superNum);
                storeList.forEach(store -> storeDTOList.add(modelMapper.map(store, StoreDTO.class)));
            }
            return storeDTOList;
        }
        return null;
    }

    @Override
    public List<StoreDTO> storeList() {
        return storeRepository.findAll().stream().map( store->modelMapper.map(store,StoreDTO.class)).collect(Collectors.toList());
    }
}