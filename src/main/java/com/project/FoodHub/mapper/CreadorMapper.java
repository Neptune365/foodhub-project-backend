package com.project.FoodHub.mapper;

import com.project.FoodHub.dto.CreadorDTO;
import com.project.FoodHub.entity.Creador;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CreadorMapper {

    private final ModelMapper modelMapper;

    public CreadorMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Creador mapToModel(CreadorDTO creadorDTO) {
        return modelMapper.map(creadorDTO, Creador.class);
    }

    public CreadorDTO mapToDTO(Creador creador) {
        return modelMapper.map(creador, CreadorDTO.class);
    }


}
