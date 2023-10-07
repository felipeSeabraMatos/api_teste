package com.apiteste.apiteste.services;
import com.apiteste.apiteste.assembler.CidadeAssembler;
import com.apiteste.apiteste.dto.CidadeDTO;
import com.apiteste.apiteste.repository.CidadeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@Log4j2
@AllArgsConstructor


public class CidadeService {

    private final CidadeRepository cidadeRepository;
    private final CidadeAssembler cidadeAssembler;
    public List<CidadeDTO> buscarCidades() {
        return cidadeAssembler.toCollectionModel(cidadeRepository.findAll());

    }


}
