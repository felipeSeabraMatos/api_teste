package com.apiteste.apiteste.services;

import com.apiteste.apiteste.model.Cliente;
import com.apiteste.apiteste.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    public List<Cliente> buscarClientes() {

        return clienteRepository.findAll();
    }

    @Transactional
    public Cliente cadastrarClientes(Cliente cliente) {

        var clientExistenteBanco = clienteRepository.findByNome(cliente.getNome());
        var clienteCadastrado = new Cliente();

        if(Objects.isNull(clientExistenteBanco)){
            clienteCadastrado = clienteRepository.save(cliente);
        }

        return clienteCadastrado;

    }
}
