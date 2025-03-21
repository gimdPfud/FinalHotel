package com.example.finalhotel.service;

import com.example.finalhotel.dto.HotelDTO;
import com.example.finalhotel.entity.Brand;
import com.example.finalhotel.entity.Hotel;
import com.example.finalhotel.repository.BrandRepository;
import com.example.finalhotel.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public HotelDTO hotelInsert(HotelDTO hotelDTO, Long brandNum) {

        //호텔 등록하기
        //먼저 본사(brand) pk 갖고오기 (갖고만 왔음)
        Brand brand = brandRepository.findById(brandNum).orElseThrow();
        log.info("갖고온 brand pk : " + brand);

        //받은 hotelDTO를 entity로 변환 후 entity에 저장 (여기에는 본사 pk 없음)
        Hotel hotel = modelMapper.map(hotelDTO, Hotel.class);

        //hotel entity에 참조한 본사(brand) pk set하기
        hotel.setBrand(brand);

        //Hotel entity에 저장하기
        Hotel hotel1 = hotelRepository.save(hotel);

        //entity를 DTO로 변환해서 반환받음
        return modelMapper.map(hotel1, HotelDTO.class);
    }
}
