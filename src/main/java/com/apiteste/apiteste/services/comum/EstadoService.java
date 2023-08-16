package com.apiteste.apiteste.services.comum;

import com.apiteste.apiteste.assembler.EstadoAssembler;
import com.apiteste.apiteste.dto.EstadoDTO;
import com.apiteste.apiteste.repository.EstadoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor

public class EstadoService {

    private final EstadoRepository estadoRepository;
    private final EstadoAssembler estadoAssembler;
    public List<EstadoDTO> buscarEstados() {
        return estadoAssembler.toCollectionModel(estadoRepository.findAll());

    }


}
