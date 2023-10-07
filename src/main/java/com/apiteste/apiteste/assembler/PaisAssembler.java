package com.apiteste.apiteste.assembler;
import com.apiteste.apiteste.dto.PaisDTO;
import com.apiteste.apiteste.model.Pais;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class PaisAssembler {

    private final ModelMapper mapper;
    public PaisDTO toModel(Pais pais){
        return mapper.map(pais, PaisDTO.class);
    }
    public Pais modelToDTO(PaisDTO paisDTO){
        return mapper.map(paisDTO, Pais.class);
    }

    public List<PaisDTO> toCollectionModel(List<Pais> pais) {
        return pais.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public List<Pais> toCollectionModelDTO(List<PaisDTO> paisDTOS) {
        return paisDTOS.stream()
                .map(this::modelToDTO)
                .collect(Collectors.toList());
    }

}
