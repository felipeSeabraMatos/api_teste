package com.apiteste.apiteste.services.comum;

import com.apiteste.apiteste.dto.comum.CepDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
@Service
public class CepService {

    private RestTemplate template = new RestTemplate();

    public CepDTO buscaEnderecoPorCep(String cep) {
        CepDTO cepDTO = null;
        try {

            cepDTO = template.getForObject("https://viacep.com.br/ws/{cep}/json", CepDTO.class, cep);
            cepDTO = validarCepRetorno(cepDTO);

        } catch (RestClientException re) {
            cepDTO = CepDTO.builder().sucesso(false).build();
        } catch (Exception e) {
            log.error("{} Erro ao processar CEP {} : Erro: ", this, cep, e);
        }

        return cepDTO;
    }

    private CepDTO validarCepRetorno(CepDTO cepDTO){
        if(Objects.isNull(cepDTO) || cepDTO.isErro())
            return CepDTO.builder().sucesso(false).build();
        cepDTO.setSucesso(true);

        return cepDTO;
    }

}