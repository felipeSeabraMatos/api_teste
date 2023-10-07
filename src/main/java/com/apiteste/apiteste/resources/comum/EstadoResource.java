package com.apiteste.apiteste.resources.comum;
import com.apiteste.apiteste.dto.EstadoDTO;
import com.apiteste.apiteste.services.comum.EstadoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/estados")
@AllArgsConstructor

public class EstadoResource {

    private final EstadoService estadoService;

    @GetMapping
    private ResponseEntity<List<EstadoDTO>> buscarEstados(){
        return ResponseEntity.ok().body(estadoService.buscarEstados());
    }


}
