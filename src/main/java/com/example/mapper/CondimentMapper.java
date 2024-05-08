package com.example.mapper;

import com.example.dto.CondimentDTO;
import com.example.entity.Condiment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CondimentMapper {
    private final ModelMapper mapper = new ModelMapper();

    public CondimentDTO toDTO(Condiment condiment) {
        return mapper.map(condiment, CondimentDTO.class);
    }

    public Condiment toEntity(CondimentDTO condimentDTO) {
        return mapper.map(condimentDTO, Condiment.class);
    }

    public List<CondimentDTO> toDTOs(List<Condiment> condiments) {
        List<CondimentDTO> condimentDTOS = new ArrayList<>();
        for (Condiment condiment : condiments) {
            condimentDTOS.add(toDTO(condiment));
        }
        return condimentDTOS;
    }

    public List<Condiment> toEntities(List<CondimentDTO> condimentDTOS) {
        List<Condiment> condiments = new ArrayList<>();
        for (CondimentDTO condimentDTO : condimentDTOS) {
            condiments.add(toEntity(condimentDTO));
        }
        return condiments;
    }
}
