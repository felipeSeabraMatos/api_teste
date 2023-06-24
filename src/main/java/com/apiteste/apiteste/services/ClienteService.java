package com.apiteste.apiteste.services;

import com.apiteste.apiteste.model.Cliente;
import com.apiteste.apiteste.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.channels.FileChannel;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> buscarClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarClientePorNome(String nome) {
        return clienteRepository.findByNomeContaining(nome);
    }

    public  Optional<Cliente> buscarClientePorId(UUID clienteId) {
        return clienteRepository.findById(clienteId);
    }

    @Transactional
    public Cliente cadastrarCliente(Cliente cliente) {

        var clientExistenteBanco = clienteRepository.findByNomeContaining(cliente.getNome());
        var clienteCadastrado = new Cliente();

        if(!clientExistenteBanco.isPresent()){
            cliente.setDataCadastro(OffsetDateTime.now());
            cliente.setAtivo(Boolean.TRUE);
            clienteCadastrado = clienteRepository.save(cliente);
        }

        return clienteCadastrado;

    }

    @Transactional
    public Cliente atualizarCliente(UUID clienteId, Cliente cliente) {
        var clienteAtualizado = new Cliente();
        var clienteExistenteBanco = clienteRepository.findById(clienteId);

        if (clienteRepository.existsById(clienteId)) {
            cliente.setId(clienteId);
            cliente.setDataCadastro(clienteExistenteBanco.get().getDataCadastro());
            cliente.setAtivo(clienteExistenteBanco.get().getAtivo());
            cliente.setDataAlteracao(OffsetDateTime.now());
            clienteAtualizado = clienteRepository.save(cliente);
        }

        return clienteAtualizado;
    }

    @Transactional
    public Cliente ativarOuInativarCliente(UUID clienteId, Boolean status) {
        var clienteAtualizado = new Cliente();
        var clienteExistenteBanco = clienteRepository.findById(clienteId).get();

         if (buscarClienteExistentePorId(clienteId)) {
            clienteExistenteBanco.setAtivo(status);
            clienteExistenteBanco.setDataAlteracao(OffsetDateTime.now());
            clienteAtualizado = clienteRepository.save(clienteExistenteBanco);
        }

        return clienteAtualizado;
    }

    @Transactional
    public void excluir(UUID clienteId) {
        if(buscarClienteExistentePorId(clienteId)){
            clienteRepository.deleteById(clienteId);
        }
     }

    private boolean buscarClienteExistentePorId(UUID clienteId) {
        return clienteRepository.existsById(clienteId);
    }

}
