package com.apiteste.apiteste.repository;

import com.apiteste.apiteste.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    Optional<Cliente> findByNomeContaining(String nome);
    Optional<Cliente> findByDocumentoDocumentoOrContatoEmail(String documento, String email);
}
