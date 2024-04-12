package com.example.mapper;

import com.example.dto.StaffDTO;
import com.example.entity.Staff;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StaffMapper {

    private final ModelMapper mapper = new ModelMapper();
    public StaffDTO toDTO(Staff staff) {
        return mapper.map(staff, StaffDTO.class);
    }
    public Staff toEntity(StaffDTO staffDTO) {
        return mapper.map(staffDTO, Staff.class);
    }
    public List<StaffDTO> toDTOs(List<Staff> staffs) {
        List<StaffDTO> staffDTOS = new ArrayList<>();
        for (Staff staff : staffs) {
            staffDTOS.add(toDTO(staff));
        }
        return staffDTOS;
    }
    public List<Staff> toEntities(List<StaffDTO> staffDTOS) {
        List<Staff> staffs = new ArrayList<>();
        for (StaffDTO staffDTO : staffDTOS) {
            staffs.add(toEntity(staffDTO));
        }
        return staffs;
    }
}
