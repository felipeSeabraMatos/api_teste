package com.apiteste.apiteste.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

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

    @Column(name = "ds_documento", length = 30 , nullable = false)
    private String documento;

    @NotEmpty
    @NotNull
    @Column(name = "ds_orgao_expedidor", length = 10, nullable = false)
    private String orgaoExpedidor;

    @NotNull
    @Column(name = "dt_emissao", nullable = false)
    private LocalDate dataEmissao;

    @Column(name = "ds_tipo_documento", length = 30)
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    @CPF
    @Column(name = "ds_cpf", length = 11)
    private String cpf;

    @CNPJ
    @Column(name = "ds_cnpj", length = 14)
    private String cnpj;

}