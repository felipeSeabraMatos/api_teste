package com.apiteste.apiteste.dto;

import com.apiteste.apiteste.model.EstadoCivil;
import com.apiteste.apiteste.model.TipoCliente;
import com.apiteste.apiteste.model.TipoDocumento;
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
    private String nome;
    private String sexo;
    private String cidade;
    private String estado;
    private String documento;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataCadastro;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataAlteracao;
    private String codigoArea;
    private Integer telefone;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean ativo;

    @Email

    private String email;

    private String orgaoExpedidor;

    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;

    private String nacionalidade;

    private String naturalidade;

    private LocalDate dataExpedicao;

    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

}
