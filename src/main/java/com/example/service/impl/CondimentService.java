package com.example.service.impl;

import com.example.dto.codiment.CondimentCreateRequest;
import com.example.dto.codiment.CondimentDTO;
import com.example.dto.codiment.CondimentResponse;
import com.example.dto.codiment.CondimentUpdateRequest;
import com.example.entity.Condiment;
import com.example.exeption.AppException;
import com.example.exeption.ErrorCode;
import com.example.mapper.CondimentMapper;
import com.example.repository.CondimentRepository;
import com.example.service.ICondimentService;
import com.example.util.ProductStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CondimentService implements ICondimentService {

    @Autowired
    private CondimentRepository condimentRepository;

    @Autowired
    private CondimentMapper condimentMapper;

    @Override
    public List<CondimentDTO> getAllCondiments() {
        List<Condiment> condiments = condimentRepository.findAll();
        return condiments.stream()
                .map(condimentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CondimentDTO save(CondimentDTO condimentDTO) {
        Condiment condiment = condimentMapper.toEntity(condimentDTO);
        condiment = condimentRepository.save(condiment);

        return condimentMapper.toDTO(condiment);
    }

    @Override
    public List<CondimentResponse> findAll() {
        return condimentMapper.toResponse(condimentRepository.findAll());
    }

    @Override
    public CondimentResponse findById(long id) {
        Condiment condiment = condimentRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.CONDIMENTS_NOT_FOUND));
        return condimentMapper.toResponse(condiment);
    }

    @Override
    public CondimentResponse createCondiment(CondimentCreateRequest condimentCreateRequest) {
        Condiment condiment = condimentMapper.toEntity(condimentCreateRequest);
        condiment.setProductStatus(ProductStatus.ABLE);
        return condimentMapper.toResponse(condimentRepository.save(condiment));
    }

    @Override
    public CondimentResponse updateCondiment(CondimentUpdateRequest condimentUpdateRequest) {
        return null;
    }
}
