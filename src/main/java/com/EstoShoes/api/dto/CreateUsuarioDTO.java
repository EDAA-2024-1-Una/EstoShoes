package com.EstoShoes.api.dto;

public record CreateUsuarioDTO(
    Integer id,
    String email,
    String nome,
    String senha
) {}
