package com.apiteste.apiteste.resources.comum;

import com.apiteste.apiteste.dto.CidadeDTO;
import com.apiteste.apiteste.services.CidadeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/cidades")
@AllArgsConstructor

public class CidadeResource {

    private final CidadeService cidadeService;

    @GetMapping
    private ResponseEntity<List<CidadeDTO>> buscarCidade() {
        return ResponseEntity.ok().body(cidadeService.buscarCidades());

    }
}


