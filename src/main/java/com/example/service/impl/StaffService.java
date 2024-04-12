package com.example.service.impl;

import com.example.dto.StaffDTO;
import com.example.entity.Staff;
import com.example.mapper.StaffMapper;
import com.example.repository.StaffRepository;
import com.example.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService implements IStaffService {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private StaffMapper staffMapper;

    @Override
    public StaffDTO findStaffById(Long id) {
        Optional<Staff> staff = staffRepository.findById(id);
        return staff.map(value -> staffMapper.toDTO(value)).orElse(null);
    }

    @Override
    public List<StaffDTO> findAll() {

        return staffMapper.toDTOs(staffRepository.findAll());
    }

    @Override
    public StaffDTO save(StaffDTO staffDTO) {
        Staff staff = staffMapper.toEntity(staffDTO);
        staff = staffRepository.save(staff);
        return staffMapper.toDTO(staff);
    }
}
