package com.apiteste.apiteste.resources;

import com.apiteste.apiteste.exception.NegocioException;
import com.apiteste.apiteste.model.Cliente;
import com.apiteste.apiteste.repository.ClienteRepository;
import com.apiteste.apiteste.services.ClienteService;
import jakarta.validation.Valid;
import jakarta.validation.executable.ValidateOnExecution;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteResource {

    private final ClienteService clienteService;
    private final ClienteRepository clienteRepository;

    @GetMapping
    private ResponseEntity<List<Cliente>> buscarClientes(){
        return ResponseEntity.ok().body(clienteService.buscarClientes());
    }
    @GetMapping("/por-nome/{nome}")
    private ResponseEntity<Cliente> buscaPorNome(@PathVariable String nome){
        return clienteService.buscarClientePorNome(nome)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/por-id/{clienteId}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable UUID clienteId) {
        return clienteService.buscarClientePorId(clienteId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseEntity<Cliente> cadastrar(@RequestBody  @Valid Cliente cliente){
        return ResponseEntity.ok().body(clienteService.cadastrarCliente(cliente));
    }
    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@PathVariable UUID clienteId,
                                                  @RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.atualizarCliente(clienteId, cliente));
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

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<String> capturar(NegocioException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
