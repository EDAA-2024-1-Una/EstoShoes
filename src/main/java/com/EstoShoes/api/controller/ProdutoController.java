package com.EstoShoes.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.EstoShoes.api.dto.ProdutoDTO;
import com.EstoShoes.api.service.ProdutoService;

@RestController
@RequestMapping("produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> getAllProdutos() {
        List<ProdutoDTO> produtos = produtoService.listar();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/ordenados")
    public ResponseEntity<List<ProdutoDTO>> listarProdutosOrdenados(
        @RequestParam(name = "campoOrdenacao", required = false) String campoOrdenacao,
        @RequestParam(name = "ordem", defaultValue = "crescente") String ordem
    ) {
        List<ProdutoDTO> produtos;
        if (campoOrdenacao != null) {
            produtos = produtoService.listarProdutosOrdenados(campoOrdenacao, ordem);
        } else {
            produtos = produtoService.listar();
        }
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> getProdutoPorId(@PathVariable int id) {
        ProdutoDTO produto = produtoService.procurarPorId(id);
        if (produto != null) {
            return ResponseEntity.ok(produto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> criarProduto(@RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO produto = produtoService.criar(produtoDTO);
        return ResponseEntity.ok(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable int id, @RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO produto = produtoService.atualizar(id, produtoDTO);
        if (produto != null) {
            return ResponseEntity.ok(produto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable int id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
