package com.EstoShoes.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EstoShoes.api.dto.ItemVendaDTO;
import com.EstoShoes.api.dto.VendaDTO;
import com.EstoShoes.api.entity.ItemVendaEntity;
import com.EstoShoes.api.entity.ProdutoEntity;
import com.EstoShoes.api.entity.VendaEntity;
import com.EstoShoes.api.repository.ClienteRepository;
import com.EstoShoes.api.repository.ProdutoRepository;
import com.EstoShoes.api.repository.UsuarioRepository;
import com.EstoShoes.api.repository.VendaRepository;

import jakarta.transaction.Transactional;

@Service
public class VendaService {
    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

     @Transactional
    public VendaDTO criar(VendaDTO vendaDTO) {
        VendaEntity vendaEntity = new VendaEntity();
        vendaEntity.setUsuario(usuarioRepository.findById(vendaDTO.idUsuario()).orElseThrow(() -> new RuntimeException("Usuário não encontrado")));
        vendaEntity.setCliente(clienteRepository.findById(vendaDTO.idCliente()).orElseThrow(() -> new RuntimeException("Cliente não encontrado")));
        vendaEntity.setValorTotal(vendaDTO.valorTotal());
        vendaEntity.setQuantidadeProdutos(vendaDTO.quantidadeProdutos());
        vendaEntity.setCreatedAt(LocalDateTime.now());

        List<ItemVendaEntity> itensVenda = vendaDTO.itensVenda().stream().map(itemDTO -> {
            ItemVendaEntity itemVendaEntity = new ItemVendaEntity();
            ProdutoEntity produtoEntity = produtoRepository.findById(itemDTO.idProduto()).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            
            if (produtoEntity.getQuantidadeEstoque() < itemDTO.quantidade()) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + produtoEntity.getNome());
            }

            produtoEntity.setQuantidadeEstoque(produtoEntity.getQuantidadeEstoque() - itemDTO.quantidade());
            produtoEntity.setQuantidadeVendida(produtoEntity.getQuantidadeVendida() + itemDTO.quantidade());
            produtoRepository.save(produtoEntity);

            itemVendaEntity.setProduto(produtoEntity);
            itemVendaEntity.setQuantidade(itemDTO.quantidade());
            itemVendaEntity.setVenda(vendaEntity);

            return itemVendaEntity;
        }).collect(Collectors.toList());

        vendaEntity.setItensVenda(itensVenda);
        vendaRepository.save(vendaEntity);

        return vendaDTO;
    }

     @Transactional
    public List<VendaDTO> listar() {
        return vendaRepository.findAll().stream()
                .map(this::converteParaDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public VendaDTO getVendaPorId(Integer id) {
        VendaEntity vendaEntity = vendaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));
        return converteParaDTO(vendaEntity);
    }

    private VendaDTO converteParaDTO(VendaEntity vendaEntity) {
        List<ItemVendaDTO> itensVendaDTO = vendaEntity.getItensVenda().stream()
                .map(item -> new ItemVendaDTO(
                        item.getProduto().getId(),
                        item.getQuantidade()))
                .collect(Collectors.toList());

        return new VendaDTO(
                vendaEntity.getId(),
                vendaEntity.getUsuario().getId(),
                vendaEntity.getCliente().getId(),
                vendaEntity.getValorTotal(),
                vendaEntity.getQuantidadeProdutos(),
                itensVendaDTO);
    }
}
