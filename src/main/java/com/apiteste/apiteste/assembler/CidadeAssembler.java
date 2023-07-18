package com.apiteste.apiteste.assembler;

import com.apiteste.apiteste.dto.CidadeDTO;
import com.apiteste.apiteste.model.Cidade;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class CidadeAssembler {

    private final ModelMapper mapper;

    public CidadeDTO toModel(Cidade cidade){
        return mapper.map(cidade, CidadeDTO.class);
    }
    public Cidade modelToDTO(CidadeDTO cidadeDTO){
        return mapper.map(cidadeDTO, Cidade.class);
    }

    public List<CidadeDTO> toCollectionModel(List<Cidade> cidade) {
        return cidade.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public List<Cidade> toCollectionModelDTO(List<CidadeDTO> cidadeDTOS) {
        return cidadeDTOS.stream()
                .map(this::modelToDTO)
                .collect(Collectors.toList());
    }

}
