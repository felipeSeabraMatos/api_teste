package com.apiteste.apiteste.repository;

import com.apiteste.apiteste.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    Cliente findByNome(String nome);
}
