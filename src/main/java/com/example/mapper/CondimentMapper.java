package com.example.mapper;

import com.example.dto.codiment.CondimentCreateRequest;
import com.example.dto.codiment.CondimentDTO;
import com.example.dto.codiment.CondimentResponse;
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
    //Moi them vao
    public List<Condiment> toEntities(List<CondimentDTO> condimentDTOS) {
        List<Condiment> condiments = new ArrayList<>();
        for (CondimentDTO condimentDTO : condimentDTOS) {
            condiments.add(toEntity(condimentDTO));
        }
        return condiments;
    }

    public Condiment toEntity(CondimentCreateRequest condimentCreateRequest) {
        return mapper.map(condimentCreateRequest, Condiment.class);
    }

    public CondimentResponse toResponse(Condiment condiment) {
        return mapper.map(condiment, CondimentResponse.class);
    }
    public List<CondimentResponse> toResponse(List<Condiment> condiments) {
        List<CondimentResponse> responses = new ArrayList<>();
        for (Condiment condiment : condiments) {
            responses.add(toResponse(condiment));
        }
        return responses;
    }
}
