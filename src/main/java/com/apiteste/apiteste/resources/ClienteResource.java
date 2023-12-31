package com.apiteste.apiteste.resources;

import com.apiteste.apiteste.dto.ClienteDTO;
import com.apiteste.apiteste.model.Cliente;
import com.apiteste.apiteste.repository.ClienteRepository;
import com.apiteste.apiteste.services.ClienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteResource {

    private final ClienteService clienteService;
    private final ClienteRepository clienteRepository;

    @GetMapping
    private ResponseEntity<List<ClienteDTO>> buscarClientes(){
        return ResponseEntity.ok().body(clienteService.buscarClientes());
    }
    @GetMapping("/por-nome/{nome}")
    private ResponseEntity<Optional<ClienteDTO>> buscaPorNome(@PathVariable String nome){
        return ResponseEntity.ok().body(clienteService.buscarClientePorNome(nome));
    }

    @GetMapping("/por-id/{clienteId}")
    public ResponseEntity<Optional<ClienteDTO>> buscarPorId(@PathVariable UUID clienteId) {
        return ResponseEntity.ok().body(clienteService.buscarClientePorId(clienteId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<ClienteDTO> cadastrar(@RequestBody @Valid ClienteDTO clienteDTO){
        return ResponseEntity.ok().body(clienteService.cadastrarCliente(clienteDTO));
    }
    @PutMapping("/{clienteId}")
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable UUID clienteId,
                                                @RequestBody ClienteDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.atualizarCliente(clienteId, clienteDTO));
    }

    @PatchMapping("/alterar-status/{clienteId}/{status}")
    public ResponseEntity<Cliente> atualizarStatus(@PathVariable UUID clienteId,
                                            @PathVariable Boolean status) {
        return ResponseEntity.ok(clienteService.ativarOuInativarCliente(clienteId, status));
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> excluir(@PathVariable UUID clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }

        clienteService.excluir(clienteId);
        return ResponseEntity.noContent().build();
    }


}
