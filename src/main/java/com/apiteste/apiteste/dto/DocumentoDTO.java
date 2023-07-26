package com.apiteste.apiteste.dto;

import com.apiteste.apiteste.model.TipoDocumento;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoDTO {

    private String documento;

    @NotEmpty
    @NotNull
    private String orgaoExpedidor;

    @NotNull
    private LocalDate dataEmissao;

    private TipoDocumento tipoDocumento;

    @CPF
    private String cpf;

    @CNPJ
    private String cnpj;


}
