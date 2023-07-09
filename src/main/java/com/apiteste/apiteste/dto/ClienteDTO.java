package com.apiteste.apiteste.dto;

import com.apiteste.apiteste.model.EstadoCivil;
import com.apiteste.apiteste.model.TipoCliente;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private UUID id;

    @NotEmpty
    @NotNull
    private String nome;

    @NotEmpty
    @NotNull
    private String sexo;

    @NotNull
    private DocumentoDTO documento;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataCadastro;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataAlteracao;

    @NotNull
    private EnderecoDTO endereco;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean ativo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;

    @NotNull
    private ContatoDTO contato;

    @NotEmpty
    @NotNull
    private String nacionalidade;

    @NotEmpty
    @NotNull
    private String naturalidade;

}
