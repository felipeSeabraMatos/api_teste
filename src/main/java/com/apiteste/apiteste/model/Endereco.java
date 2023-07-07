package com.apiteste.apiteste.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotEmpty
    @NotNull
    @Column(name = "ds_logradouro", length = 255, nullable = false)
    private String logradouro;

    @NotEmpty
    @NotNull
    @Column(name = "ds_tp_logradouro", length = 100, nullable = false)
    private String tipoLogradouro;

    @NotEmpty
    @NotNull
    @Column(name = "ds_bairro", length = 255, nullable = false)
    private String bairro;

    @NotEmpty
    @NotNull
    @Column(name = "ds_numero", length = 10, nullable = false)
    private String numero;

    @Column(name = "ds_complemento", length = 255)
    private String complemento;

    @Column(name = "ds_cep", length = 10)
    private String cep;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_cidade", referencedColumnName = "id")
    private Cidade cidade;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "fk_pais", referencedColumnName = "id")
    private Pais pais;

    @NotNull
    @ManyToOne
    @JoinColumn (name = "fk_estado" , referencedColumnName = "id")
    private Estado estado;

}
