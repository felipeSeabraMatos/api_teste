package com.apiteste.apiteste.assembler;

import com.apiteste.apiteste.dto.ClienteDTO;
import com.apiteste.apiteste.model.Cliente;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ClienteAssembler {

    private final ModelMapper mapper;

    public ClienteDTO toModel(Cliente cliente){
        return mapper.map(cliente, ClienteDTO.class);
    }
    public Cliente modelToDTO(ClienteDTO clienteDTO){
        return mapper.map(clienteDTO, Cliente.class);
    }

    public List<ClienteDTO> toCollectionModel(List<Cliente> clientes) {
        return clientes.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public List<Cliente> toCollectionModelDTO(List<ClienteDTO> clientesDTO) {
        return clientesDTO.stream()
                .map(this::modelToDTO)
                .collect(Collectors.toList());
    }

}
