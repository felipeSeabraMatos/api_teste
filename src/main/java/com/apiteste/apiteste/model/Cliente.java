package com.apiteste.apiteste.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@EqualsAndHashCode
@Entity
@Table(name = "tb_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "ds_nome", length = 150, nullable = false)
    private String nome;

    @Column(name = "ds_sexo", length = 100, nullable = false)
    private String sexo;

    @Column(name = "ds_cidade", length = 100, nullable = false)
    private String cidade;

    @Column(name = "ds_estado", length = 2, nullable = false)
    private String estado;

    @Column(name = "ds_documento", length = 14 , nullable = false)
    private String documento;

    @JsonProperty(access = Access.READ_ONLY)
    @Column(name = "dt_cadastro", nullable = false)
    private OffsetDateTime dataCadastro;

    @JsonProperty(access = Access.READ_ONLY)
    @Column(name = "dt_alteracao")
    private OffsetDateTime dataAlteracao;

    @Column(name = "nu_ddd", length = 3)
    private String codigoArea;

    @Column(name = "nu_telefone", length = 9)
    private Integer telefone;

    @JsonProperty(access = Access.READ_ONLY)
    @Column(name = "bo_ativo", length = 1, nullable = false)
    private Boolean ativo;

    @Email
    @Column(name = "ds_email", length = 100)
    private String email;

    @Column(name = "ds_orgao_expedidor", length = 10)
    private String orgaoExpedidor;

    @Column(name = "ds_estado_civil", length = 10)
    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    @Column(name = "ds_tipo_cliente", length = 25)
    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;

    @Column(name = "ds_nacionalidade", length = 20)
    private String nacionalidade;

    @Column(name = "ds_naturalidade", length = 20)
    private String naturalidade;

    @Column(name = "dt_expedicao")
    private LocalDate dataExpedicao;

    @Column(name = "ds_tipo_documento", length = 25)
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;


}