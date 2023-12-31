package com.apiteste.apiteste.repository;

import com.apiteste.apiteste.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

    Estado findBySigla(String uf);
}