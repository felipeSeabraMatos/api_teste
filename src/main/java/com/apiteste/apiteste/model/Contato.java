package com.apiteste.apiteste.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_contato")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotEmpty
    @NotNull
    @Column(name = "nu_ddd", length = 3, nullable = false)
    private String ddd;

    @NotNull
    @Column(name = "nu_telefone", length = 9, nullable = false)
    private Integer telefone;

    @NotEmpty
    @NotNull
    @Email
    @Column(name = "ds_email", length = 100, nullable = false)
    private String email;

    @NotEmpty
    @NotNull
    @Column(name = "ds_tipo_contato", nullable = false)
    private String tipoContato;

}