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
@Table(name = "tb_estado")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Estado {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty
    @NotNull
    @Column(name = "ds_nome", length = 75, nullable = false)
    private String nome;

    @NotEmpty
    @NotNull
    @Column(name = "ds_uf", length = 2, nullable = false)
    private String nomeEstado;

    @NotNull
    @Column(name = "nr_ibge", length = 2 , nullable = false)
    private Integer ibge;

    @Column(name = "ds_ddd", length = 50)
    private String ddd;

    @ManyToOne
    @JoinColumn(name = "fk_pais", referencedColumnName = "id")
    private Pais pais;
 }