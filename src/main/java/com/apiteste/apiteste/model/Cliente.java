package com.apiteste.apiteste.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_cliente")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotEmpty
    @NotNull
    @Column(name = "ds_nome", length = 150, nullable = false)
    private String nome;

    @NotEmpty
    @NotNull
    @Column(name = "ds_sexo", length = 100, nullable = false)
    private String sexo;

    @NotEmpty
    @NotNull
    @Column(name = "ds_documento", length = 14 , nullable = false)
    private String documento;

    @JsonProperty(access = Access.READ_ONLY)
    @Column(name = "dt_cadastro", nullable = false)
    private OffsetDateTime dataCadastro;

    @JsonProperty(access = Access.READ_ONLY)
    @Column(name = "dt_alteracao")
    private OffsetDateTime dataAlteracao;

    @Embedded
    private Endereco endereco;

    @NotEmpty
    @NotNull
    @Column(name = "nu_ddd", length = 3, nullable = false)
    private String codigoArea;

    @NotNull
    @Column(name = "nu_telefone", length = 9, nullable = false)
    private Integer telefone;

    @JsonProperty(access = Access.READ_ONLY)
    @Column(name = "bo_ativo", length = 1, nullable = false)
    private Boolean ativo;

    @NotEmpty
    @NotNull
    @Email
    @Column(name = "ds_email", length = 100, nullable = false)
    private String email;

    @NotEmpty
    @NotNull
    @Column(name = "ds_orgao_expedidor", length = 10, nullable = false)
    private String orgaoExpedidor;

    @NotNull
    @Column(name = "ds_estado_civil", length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    @NotNull
    @Column(name = "ds_tipo_cliente", length = 25, nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;

    @NotEmpty
    @NotNull
    @Column(name = "ds_nacionalidade", length = 20, nullable = false)
    private String nacionalidade;

    @NotEmpty
    @NotNull
    @Column(name = "ds_naturalidade", length = 20, nullable = false)
    private String naturalidade;

    @NotNull
    @Column(name = "dt_expedicao", nullable = false)
    private LocalDate dataExpedicao;

    @NotNull
    @Column(name = "ds_tipo_documento", length = 25, nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;


}