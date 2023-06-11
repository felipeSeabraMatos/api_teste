package com.apiteste.apiteste.resources;

import com.apiteste.apiteste.model.Cliente;
import com.apiteste.apiteste.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    private ResponseEntity<List<Cliente>> buscarClientes(){
        return ResponseEntity.ok().body(clienteService.buscarClientes());
    }

    @PostMapping
    private ResponseEntity<Cliente>cadastrarClientes(@RequestBody Cliente cliente){
        return ResponseEntity.ok().body(clienteService.cadastrarClientes(cliente));
    }

}
