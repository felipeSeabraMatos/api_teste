package com.apiteste.apiteste.model;

import com.apiteste.apiteste.dto.CidadeDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Endereco {

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