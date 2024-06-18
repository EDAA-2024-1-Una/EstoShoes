package com.EstoShoes.api.dto;

import java.math.BigDecimal;

public record ItemVendaDTO(
    int id,
    int idVenda,
    int idProduto,
    int quantidade,
    BigDecimal precoUnitario
) {}
