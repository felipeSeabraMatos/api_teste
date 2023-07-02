package com.apiteste.apiteste.dto;

import com.apiteste.apiteste.model.EstadoCivil;
import com.apiteste.apiteste.model.TipoCliente;
import com.apiteste.apiteste.model.TipoDocumento;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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

    @NotEmpty
    @NotNull
    private String documento;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataCadastro;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataAlteracao;

    private EnderecoDTO endereco;

    @NotEmpty
    @NotNull
    private String codigoArea;

    @NotNull
    private Integer telefone;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean ativo;

    @NotEmpty
    @NotNull
    @Email
    private String email;

    @NotEmpty
    @NotNull
    private String orgaoExpedidor;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;

    @NotEmpty
    @NotNull
    private String nacionalidade;

    @NotEmpty
    @NotNull
    private String naturalidade;

    @NotNull
    private LocalDate dataExpedicao;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

}
