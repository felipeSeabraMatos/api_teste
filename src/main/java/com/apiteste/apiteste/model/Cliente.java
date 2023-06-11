package com.apiteste.apiteste.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "ds_nome", length = 150, nullable = false)
    private String nome;

    @Column(name = "ds_sexo", length = 100, nullable = false)
    private String sexo;

    @Column(name = "ds_cidade", length = 100, nullable = false)
    private String cidade;

    @Column(name = "ds_estado", length = 2, nullable = false)
    private String estado;

    @Column(name = "ds_documento", length = 14 , nullable = false)
    private String documento;

    @Column(name = "dt_cadastro", nullable = false)
    private LocalDate dataCadastro;

    @Column(name = "dt_alteracao")
    private LocalDate dataAlteracao;

    @Column(name = "nu_ddd", length = 3)
    private String codigoArea;

    @Column(name = "nu_telefone", length = 9)
    private Integer telefone;

    @Column(name = "bo-ativo", length = 1, nullable = false)
    private Boolean ativo;

}