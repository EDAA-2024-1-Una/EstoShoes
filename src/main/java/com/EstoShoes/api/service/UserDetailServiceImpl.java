package com.EstoShoes.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.EstoShoes.api.entity.UsuarioEntity;
import com.EstoShoes.api.repository.UsuarioRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioEntity usuario = usuarioRepository.findByEmail(username).get();
		return UserDetailsImpl.build(usuario);
	}
}
