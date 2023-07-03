package com.apiteste.apiteste.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
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
public class EnderecoDTO {

    @NotEmpty
    @NotNull
    private String logradouro;

    @NotEmpty
    @NotNull
    private String tipoLogradouro;

    @NotEmpty
    @NotNull
    private String bairro;

    @NotEmpty
    @NotNull
    private String numero;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String complemento;

    private String cep;

    @NotEmpty
    @NotNull
    private String cidade;

    @NotNull
    private PaisDTO pais;

    @NotEmpty
    @NotNull
    private String estado;

}

