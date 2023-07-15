package com.apiteste.apiteste.repository;

import com.apiteste.apiteste.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, UUID>{

}