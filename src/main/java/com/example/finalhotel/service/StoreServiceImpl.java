package com.example.finalhotel.service;

import com.example.finalhotel.dto.StoreDTO;
import com.example.finalhotel.entity.Store;
import com.example.finalhotel.repository.StoreRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class StoreServiceImpl implements StoreService{
    private final StoreRepository storeRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Long brandInsert(StoreDTO storeDTO) {
        Store store = modelMapper.map(storeDTO, Store.class);
        store = storeRepository.save(store);
        return store.getStoreNum();
    }

    @Override
    public Long brandUpdate(StoreDTO storeDTO) {
        Store store = storeRepository.findById(storeDTO.getStoreNum()).orElseThrow(EntityNotFoundException::new);
        store.setStoreName(storeDTO.getStoreName());
        store.setStoreOwnerName(storeDTO.getStoreOwnerName());
        store.setStoreOwnerContact(storeDTO.getStoreOwnerContact());
        store.setIsOwn(storeDTO.getIsOwn());
        return store.getStoreNum();
    }

    @Override
    public Long brandDel(Long storeNum) {
        Store store = storeRepository.findById(storeNum).orElseThrow(EntityNotFoundException::new);
        if(store!=null){
            Long hotelNum = store.getHotel().getHotelNum();
            storeRepository.deleteById(storeNum);
            return hotelNum;
        }return null;
    }

    @Override
    public StoreDTO brandRead(Long storeNum) {
        Store store = storeRepository.findById(storeNum).orElseThrow(EntityNotFoundException::new);
        StoreDTO storeDTO = modelMapper.map(store, StoreDTO.class);
        return storeDTO;
    }

    @Override
    public List<StoreDTO> brandList(Long superNum, String hotelOrBrand, String isOwn) {
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
}