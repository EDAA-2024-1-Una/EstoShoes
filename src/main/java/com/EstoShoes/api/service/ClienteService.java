package com.EstoShoes.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EstoShoes.api.dto.ClienteDTO;
import com.EstoShoes.api.entity.ClienteEntity;
import com.EstoShoes.api.repository.ClienteRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteDTO> listar() {
        return clienteRepository.findAll().stream()
                .map(this::converteParaDto)
                .collect(Collectors.toList());
    }

    public ClienteDTO procurarPorId(int id) {
        Optional<ClienteEntity> cliente = clienteRepository.findById(id);
        return cliente.map(this::converteParaDto).orElse(null);
    }

    public ClienteDTO criar(ClienteDTO clienteDTO) {
        ClienteEntity cliente = converteParaEntity(clienteDTO);
        ClienteEntity clienteCriado = clienteRepository.save(cliente);
        return converteParaDto(clienteCriado);
    }

    public ClienteDTO atualizar(int id, ClienteDTO clienteDTO) {
        Optional<ClienteEntity> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            ClienteEntity cliente = clienteOptional.get();
            cliente.setNome(clienteDTO.nome());
            cliente.setEmail(clienteDTO.email());
            cliente.setTelefone(clienteDTO.telefone());
            cliente.setRua(clienteDTO.rua());
            cliente.setNumero(clienteDTO.numero());
            cliente.setBairro(clienteDTO.bairro());
            cliente.setCidade(clienteDTO.cidade());
            cliente.setCep(clienteDTO.cep());
            ClienteEntity clienteAtualizado = clienteRepository.save(cliente);
            return converteParaDto(clienteAtualizado);
        }
        return null;
    }

    public void deletar(int id) {
        clienteRepository.deleteById(id);
    }

    private ClienteDTO converteParaDto(ClienteEntity cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getRua(),
                cliente.getNumero(),
                cliente.getBairro(),
                cliente.getCidade(),
                cliente.getCep()
        );
    }

    private ClienteEntity converteParaEntity(ClienteDTO clienteDTO) {
        return new ClienteEntity(
                clienteDTO.id(),
                clienteDTO.nome(),
                clienteDTO.email(),
                clienteDTO.telefone(),
                clienteDTO.rua(),
                clienteDTO.numero(),
                clienteDTO.bairro(),
                clienteDTO.cidade(),
                clienteDTO.cep()
        );
    }
}
