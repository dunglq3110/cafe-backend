package com.example.mapper;

import com.example.dto.SizeDTO;
import com.example.entity.Size;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SizeMapper {
    private final ModelMapper mapper = new ModelMapper();
    public SizeDTO toDTO(Size size) {
        return mapper.map(size, SizeDTO.class);
    }
    public Size toEntity(SizeDTO sizeDTO) {
        return mapper.map(sizeDTO, Size.class);
    }
    public List<SizeDTO> toDTOs(List<Size> sizes) {
        List<SizeDTO> sizeDTOS = new ArrayList<>();
        for (Size size : sizes) {
            sizeDTOS.add(toDTO(size));
        }
        return sizeDTOS;
    }
    public List<Size> toEntities(List<SizeDTO> sizeDTOS) {
        List<Size> sizes = new ArrayList<>();
        for (SizeDTO sizeDTO : sizeDTOS) {
            sizes.add(toEntity(sizeDTO));
        }
        return sizes;
    }
}
