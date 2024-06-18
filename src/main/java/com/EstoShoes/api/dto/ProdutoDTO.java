package com.EstoShoes.api.dto;

import java.math.BigDecimal;

public record ProdutoDTO(
    int id,
    String nome,
    String cor,
    int numeracao,
    String codigo,
    String imagemUrl,
    BigDecimal valor,
    int quantidadeEstoque,
    int quantidadeVendida
) {}
