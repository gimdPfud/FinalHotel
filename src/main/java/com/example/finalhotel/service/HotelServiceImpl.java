package com.example.finalhotel.service;

import com.example.finalhotel.dto.BrandDTO;
import com.example.finalhotel.dto.HotelDTO;
import com.example.finalhotel.dto.ReplyDTO;
import com.example.finalhotel.entity.Brand;
import com.example.finalhotel.entity.Hotel;
import com.example.finalhotel.repository.BrandRepository;
import com.example.finalhotel.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public void hotelInsert(HotelDTO hotelDTO) {

        //호텔 등록하기
        //먼저 본사(brand) pk 갖고오기 (갖고만 왔음)
        Brand brand = brandRepository.findById(hotelDTO.getBrandDTO().getBrandNum()).orElseThrow();
        log.info("갖고온 brand pk : " + brand);

        //받은 hotelDTO를 entity로 변환 후 entity에 저장 (여기에는 본사 pk 없음)
        Hotel hotel = modelMapper.map(hotelDTO, Hotel.class);

        //hotel entity에 참조한 본사(brand) pk set하기
        hotel.setBrand(brand);

        //Hotel entity에 저장하기
        Hotel hotel1 = hotelRepository.save(hotel);
    }

    @Override
    public List<HotelDTO> hotelList(Long brandNum) {
        //호텔 목록
        //Brand의 pk(brandNum) 이용하여 해당 브랜드에 속한 호텔 목록 HotelDTO 리스트로 변환하여 반환
        List<Hotel> hotels = hotelRepository.findByBrand_BrandNum(brandNum);
        log.info("가져온 List : " + hotels);

        //Hotel entity를 HotelDTO로 변환
        return hotels.stream()  //hotels 리스트를 Stream API로 변환
                .map(hotel -> modelMapper.map(hotel, HotelDTO.class))
                .collect(Collectors.toList());  //변환된 DTO들을 리스트로 수집하여 반환
    }

    @Override
    public HotelDTO hotelRead(Long hotelNum) {
        return null;
    }

//    @Override
//    public HotelDTO hotelRead(Long hotelNum) {
//        BrandDTO brandDTO = new BrandDTO();
//
//        //Hotel pk로 호텔 조회
//        Optional<Brand> optionalBrand
//                = brandRepository.findById(brandDTO.getBrandNum());
//        log.info("read할 목록 : " + optionalBrand);
//
//        //optional 값이 존재하면 해당 값을 반환, 존재하지 않으면 예외 발생
////        Hotel hotel = optionalBrand.);
//
//        //entity를 DTO로 변환
//        HotelDTO hotelDTO =
//        modelMapper.map(hotel, HotelDTO.class);
//        hotelDTO.setBrandDTO( modelMapper.map(hotel.getBrand() , BrandDTO.class) );
//
//        return hotelDTO;
//    }

    @Override
    public void hotelUpdate(HotelDTO hotelDTO) {

        //Hotel pk로 호텔 조회
        Hotel hotel = hotelRepository.findById(hotelDTO.getHotelNum()).orElseThrow();

        //Hotel에 수정한 호텔명(getHotelName), 호텔내용(getHotelContent) set
        hotel.setHotelName(hotelDTO.getHotelName());
        hotel.setHotelContent(hotelDTO.getHotelContent());

        //저장
        hotelRepository.save(hotel);
    }

    @Override
    public void hotelDel(Long hotelNum) {

        hotelRepository.deleteById(hotelNum);

    }


    //인규님 필요하다고 하셔서 작성
    @Override
    public List<HotelDTO> getHotelFullList(){
        List<Hotel> hotel= hotelRepository.findAll();
        List<HotelDTO> hotelDTOList = hotel.stream()
                .map(hotellist -> modelMapper.map(hotellist, HotelDTO.class))
                .collect(Collectors.toList());
        return hotelDTOList;
    }


}
