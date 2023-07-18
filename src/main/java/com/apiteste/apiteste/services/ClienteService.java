package com.apiteste.apiteste.services;

import com.apiteste.apiteste.assembler.*;
import com.apiteste.apiteste.dto.ClienteDTO;
import com.apiteste.apiteste.dto.ContatoDTO;
import com.apiteste.apiteste.dto.DocumentoDTO;
import com.apiteste.apiteste.dto.EnderecoDTO;
import com.apiteste.apiteste.dto.comum.CepDTO;
import com.apiteste.apiteste.exception.NegocioException;
import com.apiteste.apiteste.mapper.ModelMapperConfig;
import com.apiteste.apiteste.model.Cliente;
import com.apiteste.apiteste.repository.*;
import com.apiteste.apiteste.services.comum.CepService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.nio.channels.FileChannel;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.*;

@Service
@Log4j2
@AllArgsConstructor
public class ClienteService {

    public static final int TAMANHO_CEP = 8;

    private final ClienteRepository clienteRepository;
    private final ClienteAssembler clienteAssembler;
    private final EnderecoRepository enderecoRepository;
    private final EnderecoAssembler enderecoAssembler;

    private final ContatoAssembler contatoAssembler;

    private final ContatoRepository contatoRepository;

    private final DocumentoAssembler documentoAssembler;

    private final DocumentoRepository documentoRepository;

    private final CepService cepService;
    private final EstadoRepository estadoRepository;
    private final CidadeRepository cidadeRepository;
    private final EstadoAssembler estadoAssembler;
    private final CidadeAssembler cidadeAssembler;

    public List<ClienteDTO> buscarClientes() {
        return clienteAssembler.toCollectionModel(clienteRepository.findAll());
    }

    public Optional<ClienteDTO> buscarClientePorNome(String nome) {
        var clienteExistente = clienteRepository.findByNomeContaining(nome);
        if (!clienteExistente.isPresent()) {
            throw new NegocioException("Nao foi possivel localizar o cliente");
        }
        return Optional.of(clienteAssembler.toModel(clienteExistente.get()));

    }

    public Optional<ClienteDTO> buscarClientePorId(UUID clienteId) {
        var clienteExistente = clienteRepository.findById(clienteId);
        if (!clienteExistente.isPresent()) {
            throw new NegocioException("Nao foi possivel localizar o cliente");
        }
        return Optional.of(clienteAssembler.toModel(clienteExistente.get()));
    }

    @Transactional
    public ClienteDTO cadastrarCliente(ClienteDTO clienteDTO) {

        var clienteExistenteBanco = clienteRepository.findByDocumentoDocumentoOrContatoEmail(clienteDTO.getDocumento().getDocumento(),
                                                                                           clienteDTO.getContato().getEmail());
        var clienteCadastrado = new Cliente();
        var cepDTO = new CepDTO();
        var cepFormatado = formatarCep(clienteDTO.getEndereco().getCep());

        if (clienteExistenteBanco.isPresent()) {
            throw new NegocioException("Documento ou email já cadastrados para o cliente");
        } else {
            if(clienteDTO.getEndereco().getCep().isEmpty()){
                throw new NegocioException("CEP não informado");
            } else if (
                    validarTamanhoCep(cepFormatado)) {
                throw new NegocioException("CEP informado não segue o padrão");
            } else {
                cepDTO = cepService.buscaEnderecoPorCep(cepFormatado);
                var estadoDTO = estadoAssembler.toModel(estadoRepository.findBySigla(cepDTO.getUf()) );
                var cidadeDTO = cidadeAssembler.toModel (cidadeRepository.findByNome(cepDTO.getLocalidade()));
                BeanUtils.copyProperties(cepDTO, clienteDTO.getEndereco());
                clienteDTO.getEndereco().setEstado(estadoDTO);
                clienteDTO.getEndereco().setCidade(cidadeDTO);
                clienteDTO.getEndereco().setCep(cepFormatado);
                var enderecoDTO = cadastrarEndereco(clienteDTO.getEndereco());
                var documentoDTO = cadastrarDocumento(clienteDTO.getDocumento());
                var contatoDTO = cadastrarContato(clienteDTO.getContato());

                clienteDTO.setDataCadastro(OffsetDateTime.now());
                clienteDTO.setAtivo(Boolean.TRUE);
                clienteDTO.setEndereco(enderecoDTO);
                clienteDTO.setDocumento(documentoDTO);
                clienteDTO.setContato(contatoDTO);
                clienteCadastrado = clienteRepository.save(clienteAssembler.modelToDTO(clienteDTO));
            }
        }

      return clienteAssembler.toModel(clienteCadastrado);
    }

    @Transactional
    public ClienteDTO atualizarCliente(UUID clienteId, ClienteDTO clienteDTO) {
        var clienteExistenteBanco = clienteRepository.findById(clienteId);
        var clienteAtualizado = new ClienteDTO();

        if (!clienteExistenteBanco.isPresent()) {
            throw new NegocioException("Cliente não cadastrado!");
        }

        if (clienteRepository.existsById(clienteId) && clienteExistenteBanco.isPresent()) {
            clienteDTO.setId(clienteExistenteBanco.get().getId());
            clienteDTO.setDataAlteracao(OffsetDateTime.now());
            clienteDTO.setDataCadastro(clienteExistenteBanco.get().getDataCadastro());
            clienteDTO.setAtivo(clienteExistenteBanco.get().getAtivo());
            clienteDTO.setDocumento(documentoAssembler.toModel(clienteExistenteBanco.get().getDocumento()));

            clienteAtualizado = clienteAssembler.toModel(clienteRepository.saveAndFlush(clienteAssembler.modelToDTO(clienteDTO)));
        }

        return clienteAtualizado;
    }

    @Transactional
    public Cliente ativarOuInativarCliente(UUID clienteId, Boolean status) {
        var clienteAtualizado = new Cliente();
        var clienteExistenteBanco = clienteRepository.findById(clienteId).get();

        if (buscarClienteExistentePorId(clienteId)) {
            clienteExistenteBanco.setAtivo(status);
            clienteExistenteBanco.setDataAlteracao(OffsetDateTime.now());
            clienteAtualizado = clienteRepository.save(clienteExistenteBanco);
        }

        return clienteAtualizado;
    }

    @Transactional
    public void excluir(UUID clienteId) {

        clienteRepository.deleteById(clienteId);
    }

    private boolean buscarClienteExistentePorId(UUID clienteId) {
        return clienteRepository.existsById(clienteId);
    }
    protected EnderecoDTO cadastrarEndereco(EnderecoDTO enderecoDTO) {
        var enderecoCadastrado = enderecoRepository.save(enderecoAssembler.modelToDTO(enderecoDTO));
        return enderecoAssembler.toModel(enderecoCadastrado);
    }

    protected DocumentoDTO cadastrarDocumento(DocumentoDTO documentoDTO) {
        var documentoCadastrado = documentoRepository.save(documentoAssembler.modelToDTO(documentoDTO));
        return documentoAssembler.toModel(documentoCadastrado);
    }

    protected ContatoDTO cadastrarContato(ContatoDTO contatoDTO) {
        var contatoCadastrado = contatoRepository.save(contatoAssembler.modelToDTO(contatoDTO));
        return contatoAssembler.toModel(contatoCadastrado);
    }

    protected boolean validarTamanhoCep(String cep) {
        return cep.length() < TAMANHO_CEP || cep.length() > TAMANHO_CEP;
    }

    public String formatarCep(String cep){
        cep = cep.replaceAll("\\.","");
        cep = cep.replaceAll("-", "");
        return cep;
    }
}
