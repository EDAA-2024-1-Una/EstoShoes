package com.EstoShoes.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.EstoShoes.api.dto.CreateUsuarioDTO;
import com.EstoShoes.api.dto.UsuarioDTO;
import com.EstoShoes.api.entity.UsuarioEntity;
import com.EstoShoes.api.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
	private PasswordEncoder passwordEncoder;

    public UsuarioDTO procurarPorId(int id) {
        UsuarioEntity usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        return converteParaDto(usuario);
    }

    public UsuarioDTO criar(CreateUsuarioDTO usuarioDTO) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setNome(usuarioDTO.nome());
        usuario.setEmail(usuarioDTO.email());
        usuario.setSenha(passwordEncoder.encode(usuarioDTO.senha()));
        usuario = usuarioRepository.save(usuario);
        return converteParaDto(usuario);
    }

    private UsuarioDTO converteParaDto(UsuarioEntity usuario) {
        return new UsuarioDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail()
        );
    }
}
