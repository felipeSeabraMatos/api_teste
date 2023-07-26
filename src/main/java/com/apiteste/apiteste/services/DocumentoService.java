package com.apiteste.apiteste.services;

import com.apiteste.apiteste.assembler.DocumentoAssembler;
import com.apiteste.apiteste.dto.ClienteDTO;
import com.apiteste.apiteste.dto.DocumentoDTO;
import com.apiteste.apiteste.exception.NegocioException;
import com.apiteste.apiteste.model.TipoCliente;
import com.apiteste.apiteste.model.TipoDocumento;
import com.apiteste.apiteste.repository.DocumentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class DocumentoService {

    private static final int TAMANHO_DOCUMENTO_RIO_SP = 8;
    private static final int TAMANHO_DOCUMENTO_DEMAIS_CIDADES = 7;

    private final DocumentoAssembler documentoAssembler;
    private final DocumentoRepository documentoRepository;

    DocumentoDTO validarDocumentoTipoPessoa(ClienteDTO clienteDTO) {
        DocumentoDTO documentoDTO = null;

        if (clienteDTO.getTipoCliente().equals(TipoCliente.PESSOA_JURIDICA)) {
            if (clienteDTO.getDocumento().getTipoDocumento().equals(TipoDocumento.CNPJ) &&
                   verificarTamanhoDocumentoCliente(clienteDTO, 14)) {
                throw new NegocioException("O CNPJ informado é inválido");
            } else if(clienteDTO.getDocumento().getDocumento().length() == 14){
                documentoDTO = cadastrarDocumento(clienteDTO.getDocumento());
            }
        } else{
            if (clienteDTO.getDocumento().getTipoDocumento().equals(TipoDocumento.CPF) &&
                    verificarTamanhoDocumentoCliente(clienteDTO, 11)) {
                throw new NegocioException("O CPF informado é inválido");
            } else if (clienteDTO.getDocumento().getTipoDocumento().equals(TipoDocumento.CPF) &&
                    clienteDTO.getDocumento().getDocumento().length() == 11){
                documentoDTO = cadastrarDocumento(clienteDTO.getDocumento());
            }

        }

        return Objects.isNull(documentoDTO) ? clienteDTO.getDocumento() : documentoDTO;
    }

    DocumentoDTO validarRGCliente(ClienteDTO clienteDTO) {
        DocumentoDTO documentoDTO = null;

        if(verficaSeDocumentoIgualRG(clienteDTO)) {
            if(verificarCidadeCliente(clienteDTO)) {
                var tamanhoDocumento = clienteDTO.getDocumento().getDocumento().length() < TAMANHO_DOCUMENTO_RIO_SP;

                if(tamanhoDocumento) {
                    throw new NegocioException("RG Informado não condiz com o estado do RJ/SP");
                } else {
                    documentoDTO = cadastrarDocumento(clienteDTO.getDocumento());
                }
            } else {
                var tamanhoDocumento = clienteDTO.getDocumento().getDocumento().length() < TAMANHO_DOCUMENTO_DEMAIS_CIDADES;

                if(tamanhoDocumento) {
                    throw new NegocioException("RG Informado não condiz com os demais estados");
                } else {
                    documentoDTO = cadastrarDocumento(clienteDTO.getDocumento());
                }
            }
        }

        return Objects.isNull(documentoDTO) ? clienteDTO.getDocumento() : documentoDTO;
    }

    private boolean verficaSeDocumentoIgualRG(ClienteDTO clienteDTO) {
        return clienteDTO.getTipoCliente().equals(TipoCliente.PESSOA_FISICA) &&
                clienteDTO.getDocumento().getTipoDocumento().equals(TipoDocumento.RG);
    }

    private boolean verificarCidadeCliente(ClienteDTO clienteDTO) {
        return clienteDTO.getEndereco().getEstado().getSigla().equals("RJ") ||
                clienteDTO.getEndereco().getEstado().getSigla().equals("SP");
    }

    private boolean verificarTamanhoDocumentoCliente(ClienteDTO clienteDTO, Integer tamanhoDocumento) {
        return  clienteDTO.getDocumento().getDocumento().length() < tamanhoDocumento ||
                clienteDTO.getDocumento().getDocumento().length() > tamanhoDocumento;
    }

    DocumentoDTO validarDocumentoPessoaFisica(ClienteDTO clienteDTO) {
        var documentoDTO = validarRGCliente(clienteDTO);

        return documentoDTO;
    }

    protected DocumentoDTO cadastrarDocumento(DocumentoDTO documentoDTO) {
        var documentoCadastrado = documentoRepository.save(documentoAssembler.modelToDTO(documentoDTO));
        return documentoAssembler.toModel(documentoCadastrado);
    }

}
