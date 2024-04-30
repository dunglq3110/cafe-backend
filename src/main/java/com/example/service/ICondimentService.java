package com.example.service;

import com.example.dto.CondimentDTO;

import java.util.List;

public interface ICondimentService {

    List<CondimentDTO> getAllCondiments();
    CondimentDTO save(CondimentDTO condimentDTO);
    List<CondimentDTO> findAll();
    CondimentDTO findById(long id);
}
