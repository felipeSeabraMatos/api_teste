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

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoDTO {

    @NotEmpty
    @NotNull
    private String documento;

    @NotEmpty
    @NotNull
    private String orgaoExpedidor;

    @NotNull
    private LocalDate dataEmissao;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

}
