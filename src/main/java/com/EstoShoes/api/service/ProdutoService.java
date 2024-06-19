package com.EstoShoes.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EstoShoes.api.dto.ProdutoDTO;
import com.EstoShoes.api.entity.ProdutoEntity;
import com.EstoShoes.api.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDTO> listar() {
        return produtoRepository.findAll().stream()
                .map(this::converteParaDto)
                .collect(Collectors.toList());
    }

    public ProdutoDTO procurarPorId(int id) {
        Optional<ProdutoEntity> produto = produtoRepository.findById(id);
        return produto.map(this::converteParaDto).orElse(null);
    }

    public ProdutoDTO criar(ProdutoDTO produtoDTO) {
        ProdutoEntity produto = converteParaEntity(produtoDTO);
        ProdutoEntity produtoCriado = produtoRepository.save(produto);
        return converteParaDto(produtoCriado);
    }

    public ProdutoDTO atualizar(int id, ProdutoDTO produtoDTO) {
        Optional<ProdutoEntity> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isPresent()) {
            ProdutoEntity produto = produtoOptional.get();
            produto.setNome(produtoDTO.nome());
            produto.setCor(produtoDTO.cor());
            produto.setNumeracao(produtoDTO.numeracao());
            produto.setCodigo(produtoDTO.codigo());
            produto.setImagemUrl(produtoDTO.imagemUrl());
            produto.setValor(produtoDTO.valor());
            produto.setQuantidadeEstoque(produtoDTO.quantidadeEstoque());
            produto.setQuantidadeVendida(produtoDTO.quantidadeVendida());
            ProdutoEntity updatedProduto = produtoRepository.save(produto);
            return converteParaDto(updatedProduto);
        }
        return null;
    }

    public void deletar(int id) {
        produtoRepository.deleteById(id);
    }

    private ProdutoDTO converteParaDto(ProdutoEntity produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getCor(),
                produto.getNumeracao(),
                produto.getCodigo(),
                produto.getImagemUrl(),
                produto.getValor(),
                produto.getQuantidadeEstoque(),
                produto.getQuantidadeVendida()
        );
    }

    private ProdutoEntity converteParaEntity(ProdutoDTO produtoDTO) {
        return new ProdutoEntity(
                produtoDTO.id(),
                produtoDTO.nome(),
                produtoDTO.cor(),
                produtoDTO.numeracao(),
                produtoDTO.codigo(),
                produtoDTO.imagemUrl(),
                produtoDTO.valor(),
                produtoDTO.quantidadeEstoque(),
                produtoDTO.quantidadeVendida()
        );
    }
}
