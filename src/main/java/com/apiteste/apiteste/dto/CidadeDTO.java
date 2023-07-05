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
public class CidadeDTO {

    private Long id;

    @NotEmpty
    @NotNull
    private String nome;

    @NotNull
    private EstadoDTO estado;

    @NotNull
    private Integer ibge;

}
