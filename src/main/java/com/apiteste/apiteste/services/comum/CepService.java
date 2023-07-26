package com.apiteste.apiteste.services.comum;

import com.apiteste.apiteste.dto.ClienteDTO;
import com.apiteste.apiteste.dto.comum.CepDTO;
import com.apiteste.apiteste.exception.NegocioException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
@Service
public class CepService {
    private static final int TAMANHO_CEP = 8;
    public static final String ENDERECO_VIA_CEP = "https://viacep.com.br/ws/{cep}/json";
    private RestTemplate template = new RestTemplate();

    public CepDTO buscaEnderecoPorCep(String cep) {
        CepDTO cepDTO = null;
        try {

            cepDTO = template.getForObject(ENDERECO_VIA_CEP, CepDTO.class, cep);
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

    public void validarCep(ClienteDTO clienteDTO, String cepFormatado) {
        if(clienteDTO.getEndereco().getCep().isEmpty()){
            throw new NegocioException("CEP não informado");
        } else if (validarTamanhoCep(cepFormatado)) {
            throw new NegocioException("CEP informado não segue o padrão");
        }
    }
    public boolean validarTamanhoCep(String cep) {
        return cep.length() < TAMANHO_CEP || cep.length() > TAMANHO_CEP;
    }

    public String formatarCep(String cep){
        cep = cep.replaceAll("\\.","");
        cep = cep.replaceAll("-", "");
        return cep;
    }

}