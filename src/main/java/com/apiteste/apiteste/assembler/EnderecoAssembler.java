package com.apiteste.apiteste.assembler;

import com.apiteste.apiteste.dto.EnderecoDTO;
import com.apiteste.apiteste.model.Endereco;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EnderecoAssembler {

    private final ModelMapper mapper;

    public EnderecoDTO toModel(Endereco endereco){
        return mapper.map(endereco, EnderecoDTO.class);
    }
    public Endereco modelToDTO(EnderecoDTO enderecoDTO){
        return mapper.map(enderecoDTO, Endereco.class);
    }

    public List<EnderecoDTO> toCollectionModel(List<Endereco> enderecos) {
        return enderecos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public List<Endereco> toCollectionModelDTO(List<EnderecoDTO> enderecoDTO) {
        return enderecoDTO.stream()
                .map(this::modelToDTO)
                .collect(Collectors.toList());
    }

}
