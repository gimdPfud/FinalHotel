package com.example.finalhotel.service;

import com.example.finalhotel.dto.BrandDTO;
import com.example.finalhotel.dto.MemberDTO;
import com.example.finalhotel.entity.Brand;
import com.example.finalhotel.entity.Member;
import com.example.finalhotel.repository.BrandRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor

public class BrandServiceImpl implements BrandService{

    private  final BrandRepository brandRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public BrandDTO insert(BrandDTO brandDTO) {

        log.info("본사 등록 서비스 진입" + brandDTO);

        Brand brand = modelMapper.map(brandDTO, Brand.class);

        brand = brandRepository.save(brand);

        brandDTO = modelMapper.map(brand, BrandDTO.class);

        log.info("본사 등록 dto 끝" + brandDTO);

        return brandDTO;

    }


    @Override
    public BrandDTO read(Long num) {
        log.info("본사 읽기 서비스 진입" + num);

        Optional<Brand> optionalBrand =
                brandRepository.findById(num);

        Brand brand =
                optionalBrand.get();

        BrandDTO brandDTO =
                modelMapper.map(brand, BrandDTO.class);

        log.info("변환된 읽기 dto" + brandDTO);

        return brandDTO;
    }

    @Override
    public List<BrandDTO> brandDTOList (String email) {
        log.info("본사 목록 서비스 진입");

        List<Brand> brandList =
                brandRepository.findByMember_MemberEmail(email);

        List<BrandDTO> brandDTOList = new ArrayList<>();

        for (Brand brand : brandList){

            BrandDTO brandDTO = modelMapper.map(brand, BrandDTO.class);
            brandDTOList.add(brandDTO);

        }

        return brandDTOList;

    }

    @Override
    public BrandDTO update(BrandDTO brandDTO) {
        log.info("본사 업데이트 서비스 진입" + brandDTO.getBrandNum());
        log.info("본사 업데이트 서비스 진입" + brandDTO.getBrandTitle());
        log.info("본사 업데이트 서비스 진입" + brandDTO.getBrandContent());

        Optional<Brand> optionalBrand =
                brandRepository.findById(brandDTO.getBrandNum());

        Brand brand
                 = optionalBrand.get();

        brand.setBrandTitle(brandDTO.getBrandTitle());
        brand.setBrandContent(brandDTO.getBrandContent());

        log.info("업데이트 완료" + brandDTO);

        return brandDTO;
    }

    @Override
    public Long del(Long num) {
        log.info("삭제 서비스 진입");

        brandRepository.deleteById(num);

        log.info("삭제 완료 db를 확인하세요");

        return num;

    }
}
