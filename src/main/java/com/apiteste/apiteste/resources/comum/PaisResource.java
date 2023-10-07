package com.apiteste.apiteste.resources.comum;
import com.apiteste.apiteste.dto.PaisDTO;
import com.apiteste.apiteste.services.PaisService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/pais")
@AllArgsConstructor

public class PaisResource {

    private final PaisService paisService;

    @GetMapping
    private ResponseEntity<List<PaisDTO>> buscarPais(){
        return ResponseEntity.ok().body(paisService.buscarPais());
    }

}
