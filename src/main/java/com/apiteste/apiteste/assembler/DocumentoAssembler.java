package com.apiteste.apiteste.assembler;

import com.apiteste.apiteste.dto.DocumentoDTO;
import com.apiteste.apiteste.model.Documento;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class DocumentoAssembler {

    private final ModelMapper mapper;

    public DocumentoDTO toModel(Documento documento){
        return mapper.map(documento, DocumentoDTO.class);
    }
    public Documento modelToDTO(DocumentoDTO documentoDTO){
        return mapper.map(documentoDTO, Documento.class);
    }

    public List<DocumentoDTO> toCollectionModel(List<Documento> documentos) {
        return documentos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public List<Documento> toCollectionModelDTO(List<DocumentoDTO> documentoDTOS) {
        return documentoDTOS.stream()
                .map(this::modelToDTO)
                .collect(Collectors.toList());
    }

}
