package com.example.service;

import com.example.dto.StaffDTO;

import java.util.List;
import java.util.Optional;

public interface IStaffService {

    StaffDTO findStaffById(Long id);
    List<StaffDTO> findAll();
    StaffDTO save(StaffDTO staffDTO);
}
