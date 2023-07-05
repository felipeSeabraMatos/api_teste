package com.apiteste.apiteste.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstadoDTO {

    private Long id;

    @NotEmpty
    @NotNull
    private String nome;

    @NotEmpty
    @NotNull
    private String nomeEstado;

    @NotNull
    private Integer ibge;

    private String ddd;

    private PaisDTO pais;
}