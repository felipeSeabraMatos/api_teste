package com.apiteste.apiteste.assembler;

import com.apiteste.apiteste.dto.EstadoDTO;
import com.apiteste.apiteste.model.Estado;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EstadoAssembler {

    private final ModelMapper mapper;

    public EstadoDTO toModel(Estado estado){
        return mapper.map(estado, EstadoDTO.class);
    }
    public Estado modelToDTO(EstadoDTO estadoDTO){
        return mapper.map(estadoDTO, Estado.class);
    }

    public List<EstadoDTO> toCollectionModel(List<Estado> estados) {
        return estados.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public List<Estado> toCollectionModelDTO(List<EstadoDTO> estadoDTOS) {
        return estadoDTOS.stream()
                .map(this::modelToDTO)
                .collect(Collectors.toList());
    }

}
