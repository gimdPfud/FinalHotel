package com.example.finalhotel.service;

import com.example.finalhotel.dto.BrandDTO;
import com.example.finalhotel.entity.Brand;
import com.example.finalhotel.repository.BrandRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<BrandDTO> breandList() {
        return null;
    }

    @Override
    public BrandDTO read(Long num) {
        return null;
    }

    @Override
    public BrandDTO update(BrandDTO brandDTO) {
        return null;
    }

    @Override
    public Long del(Long num) {
        return null;
    }
}
