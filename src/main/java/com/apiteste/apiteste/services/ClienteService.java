package com.apiteste.apiteste.services;

import com.apiteste.apiteste.assembler.*;
import com.apiteste.apiteste.dto.ClienteDTO;
import com.apiteste.apiteste.dto.ContatoDTO;
import com.apiteste.apiteste.dto.DocumentoDTO;
import com.apiteste.apiteste.dto.EnderecoDTO;
import com.apiteste.apiteste.exception.NegocioException;
import com.apiteste.apiteste.model.Cliente;
import com.apiteste.apiteste.repository.*;
import com.apiteste.apiteste.services.comum.CepService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteAssembler clienteAssembler;
    private final EnderecoRepository enderecoRepository;
    private final EnderecoAssembler enderecoAssembler;
    private final ContatoAssembler contatoAssembler;
    private final ContatoRepository contatoRepository;
    private final DocumentoAssembler documentoAssembler;
    private final CepService cepService;
    private final DocumentoService documentoService;
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
        var cepFormatado = cepService.formatarCep(clienteDTO.getEndereco().getCep());

        if (clienteExistenteBanco.isPresent()) {
            throw new NegocioException("Documento ou email já cadastrados para o cliente");
        } else {
            cepService.validarCep(clienteDTO, cepFormatado);
            var builderClienteDTO = builderCliente(clienteDTO);
            var builderEnderecoDTO = builderEndereco(builderClienteDTO, cepFormatado);
            var enderecoDTO = cadastrarEndereco(builderEnderecoDTO);
            builderClienteDTO.setEndereco(enderecoDTO);
            clienteCadastrado = clienteRepository.save(clienteAssembler.modelToDTO(builderClienteDTO));
        }

        return clienteAssembler.toModel(clienteCadastrado);
    }

    private ClienteDTO builderCliente(ClienteDTO clienteDTO) {
        var documentoDTO = documentoService.validarDocumentoTipoPessoa(clienteDTO);

        var contatoDTO = cadastrarContato(clienteDTO.getContato());

        clienteDTO.setDataCadastro(OffsetDateTime.now());
        clienteDTO.setAtivo(Boolean.TRUE);
        clienteDTO.setDocumento(documentoDTO);
        clienteDTO.setContato(contatoDTO);
        return clienteDTO;
    }

    private EnderecoDTO builderEndereco(ClienteDTO clienteDTO, String cepFormatado) {
        var cepDTO = cepService.buscaEnderecoPorCep(cepFormatado);
        if (cepDTO.isErro() == Boolean.FALSE && cepDTO.getSucesso() == Boolean.FALSE){
            throw new NegocioException("Cep não encontrado!");
        } else {
            var estadoDTO = estadoAssembler.toModel(estadoRepository.findBySigla(cepDTO.getUf()) );
            var cidadeDTO = cidadeAssembler.toModel (cidadeRepository.findByNome(cepDTO.getLocalidade()));
            clienteDTO.getEndereco().setEstado(estadoDTO);
            clienteDTO.getEndereco().setCidade(cidadeDTO);
            clienteDTO.getEndereco().setCep(cepFormatado);
            clienteDTO.getEndereco().setComplemento(cepDTO.getComplemento());
            clienteDTO.getEndereco().setLogradouro(cepDTO.getLogradouro());
            clienteDTO.getEndereco().setBairro(cepDTO.getBairro());
            var documentoDTO = documentoService.validarDocumentoPessoaFisica(clienteDTO);
            clienteDTO.setDocumento(documentoDTO);

            return clienteDTO.getEndereco();

        }

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

    protected ContatoDTO cadastrarContato(ContatoDTO contatoDTO) {
        var contatoCadastrado = contatoRepository.save(contatoAssembler.modelToDTO(contatoDTO));
        return contatoAssembler.toModel(contatoCadastrado);
    }

}
