package com.apiteste.apiteste.services;

import com.apiteste.apiteste.assembler.PaisAssembler;
import com.apiteste.apiteste.dto.PaisDTO;
import com.apiteste.apiteste.repository.PaisRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Log4j2
@AllArgsConstructor


public class PaisService {

    private final PaisRepository paisRepository;

    private final PaisAssembler paisAssembler;

    public List<PaisDTO> buscarPais() {
        return paisAssembler.toCollectionModel(paisRepository.findAll());

    }


}
