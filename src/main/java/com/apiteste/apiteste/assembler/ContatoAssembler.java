package com.apiteste.apiteste.assembler;

import com.apiteste.apiteste.dto.ContatoDTO;
import com.apiteste.apiteste.model.Contato;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ContatoAssembler {

    private final ModelMapper mapper;

    public ContatoDTO toModel(Contato contato){
        return mapper.map(contato, ContatoDTO.class);
    }
    public Contato modelToDTO(ContatoDTO contatoDTO){
        return mapper.map(contatoDTO, Contato.class);
    }

    public List<ContatoDTO> toCollectionModel(List<Contato> contatos) {
        return contatos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public List<Contato> toCollectionModelDTO(List<ContatoDTO> contatoDTOS) {
        return contatoDTOS.stream()
                .map(this::modelToDTO)
                .collect(Collectors.toList());
    }

}
