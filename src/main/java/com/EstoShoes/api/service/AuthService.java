package com.EstoShoes.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.EstoShoes.api.dto.AcessDTO;
import com.EstoShoes.api.dto.AuthenticationDTO;
import com.EstoShoes.api.security.jwt.JwtUtils;

@Service
public class AuthService {
    @Autowired
	private AuthenticationManager authenticatioManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	public AcessDTO login(AuthenticationDTO authDto) {
		
		try {
            UsernamePasswordAuthenticationToken userAuth = 
                    new UsernamePasswordAuthenticationToken(authDto.email(), authDto.senha());

            Authentication authentication = authenticatioManager.authenticate(userAuth);
            
            UserDetailsImpl userAuthenticate = (UserDetailsImpl)authentication.getPrincipal();
            
            String token = jwtUtils.generateTokenFromUserDetailsImpl(userAuthenticate);
            
            AcessDTO accessDto = new AcessDTO(token);
            
            return accessDto;
		
		}catch(BadCredentialsException e) {
			System.out.println("Email ou senha errado");
		}
		return new AcessDTO("Acesso negado");
	}
}
