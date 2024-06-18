package com.EstoShoes.api.dto;

public record ClienteDTO(
    int id,
    String nome,
    String email,
    String telefone,
    String rua,
    String numero,
    String bairro,
    String cidade,
    String cep
) {}
