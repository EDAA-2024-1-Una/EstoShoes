package com.EstoShoes.api.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.EstoShoes.api.entity.UsuarioEntity;

public class UserDetailsImpl implements UserDetails {
    private int id;
	private String name;	
	private String email;
	private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(int id, String name, String password, String email,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.authorities = authorities;
	}

    public static UserDetailsImpl build(UsuarioEntity usuario) {
		
		return new UserDetailsImpl(
				usuario.getId(), 
				usuario.getNome(),
				usuario.getSenha(),
				usuario.getEmail(), 
				new ArrayList<>());
	}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

}
