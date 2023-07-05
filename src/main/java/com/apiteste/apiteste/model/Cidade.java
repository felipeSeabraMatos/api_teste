package com.apiteste.apiteste.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_cidade")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cidade {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotEmpty
    @NotNull
    @Column(name = "ds_nome", length = 75, nullable = false)
    private String nome;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_uf", referencedColumnName = "id")
    private Estado estado;

    @NotNull
    @Column(name = "nr_ibge", length = 2 , nullable = false)
    private Integer ibge;

 }