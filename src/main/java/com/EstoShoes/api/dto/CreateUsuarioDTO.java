package com.EstoShoes.api.dto;

public record CreateUsuarioDTO(
    String email,
    String nome,
    String senha
) {}
