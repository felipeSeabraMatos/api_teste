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
@Table(name = "tb_pais")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pais {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotEmpty
    @NotNull
    @Column(name = "ds_nome", length = 60, nullable = false)
    private String nome;
 }