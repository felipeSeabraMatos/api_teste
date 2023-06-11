package com.apiteste.apiteste.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_cliente")
public class Cliente {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "ds_nome", nullable = false)
    private String nome;
    @Column(name = "ds_cidade", nullable = false)
    private String cidade;
    @Column(name = "ds_estado", nullable = false)
    private String estado;
    @Transient
    private String documento;
    @Transient
    private LocalDate dataCadastro;
    @Transient
    private LocalDate dataAlteracao;
    @Transient
    private Integer codigoArea;
    @Transient
    private Integer telefone;
    @Transient
    private Boolean ativo;



}