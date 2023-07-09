package com.apiteste.apiteste.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_documento")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotEmpty
    @NotNull
    @Column(name = "ds_documento", length = 14 , nullable = false)
    private String documento;

    @NotEmpty
    @NotNull
    @Column(name = "ds_orgao_expedidor", length = 10, nullable = false)
    private String orgaoExpedidor;

    @NotNull
    @Column(name = "dt_emissao", nullable = false)
    private LocalDate dataEmissao;

    @NotNull
    @Column(name = "ds_tipo_documento", length = 25, nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

}