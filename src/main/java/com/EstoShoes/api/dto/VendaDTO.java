package com.EstoShoes.api.dto;

import java.math.BigDecimal;
import java.util.List;

public record VendaDTO(
    int id,
    int idUsuario,
    int idCliente,
    BigDecimal valorTotal,
    int quantidadeProdutos,
    List<ItemVendaDTO> itensVenda
) {}
