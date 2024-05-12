package com.example.service;

import com.example.dto.codiment.CondimentCreateRequest;
import com.example.dto.codiment.CondimentDTO;
import com.example.dto.codiment.CondimentResponse;
import com.example.dto.codiment.CondimentUpdateRequest;

import java.util.List;

public interface ICondimentService {

    List<CondimentDTO> getAllCondiments();
    CondimentDTO save(CondimentDTO condimentDTO);



    List<CondimentResponse> findAll();
    CondimentResponse findById(long id);
    CondimentResponse createCondiment(CondimentCreateRequest condimentCreateRequest);
    CondimentResponse updateCondiment(CondimentUpdateRequest condimentUpdateRequest);
}
