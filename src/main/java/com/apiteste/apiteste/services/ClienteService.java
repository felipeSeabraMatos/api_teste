package com.apiteste.apiteste.services;

import com.apiteste.apiteste.dto.ClienteDTO;
import com.apiteste.apiteste.exception.NegocioException;
import com.apiteste.apiteste.model.Cliente;
import com.apiteste.apiteste.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.channels.FileChannel;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteDTO> buscarClientes() {
        var clientes = clienteRepository.findAll();
        List<ClienteDTO> clientesDTOList = new ArrayList<>();
        clientes.stream().forEach(cliente -> {
            var clienteDTO = new ClienteDTO();
            clienteDTO.setId(cliente.getId());
            clienteDTO.setNome(cliente.getNome());
            clienteDTO.setSexo(cliente.getSexo());
            clienteDTO.setCidade(cliente.getCidade());
            clientesDTOList.add(clienteDTO);
        });

        return clientesDTOList;
    }

    public Optional<Cliente> buscarClientePorNome(String nome) {
        var clienteExistente = clienteRepository.findByNomeContaining(nome);
        if (!clienteExistente.isPresent()) {
            throw new NegocioException("Nao foi possivel localizar o cliente");
        }
        return clienteExistente;

    }

    public Optional<Cliente> buscarClientePorId(UUID clienteId) {
        var clienteExistente = clienteRepository.findById(clienteId);
        if (!clienteExistente.isPresent()) {
            throw new NegocioException("Nao foi possivel localizar o cliente");
        }
        return clienteExistente;
    }

    @Transactional
    public Cliente cadastrarCliente(Cliente cliente) {

        var clientExistenteBanco = clienteRepository.findByNomeContaining(cliente.getNome());
        var clienteCadastrado = new Cliente();

        if (clientExistenteBanco.isPresent()) {
            throw new NegocioException("Cliente ja cadastrado");
        } else {
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

        clienteRepository.deleteById(clienteId);
    }

    private boolean buscarClienteExistentePorId(UUID clienteId) {
        return clienteRepository.existsById(clienteId);
    }

}
