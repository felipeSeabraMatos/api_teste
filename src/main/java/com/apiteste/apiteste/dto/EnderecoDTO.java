package com.apiteste.apiteste.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String logradouro;

    @NotEmpty
    @NotNull
    private String tipoLogradouro;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String bairro;

    @NotEmpty
    @NotNull
    private String numero;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String complemento;

    @NotNull
    @NotEmpty
    private String cep;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private CidadeDTO cidade;

    @NotNull
    private PaisDTO pais;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private EstadoDTO estado;

}

