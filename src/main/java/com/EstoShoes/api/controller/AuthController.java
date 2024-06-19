package com.EstoShoes.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EstoShoes.api.dto.AuthenticationDTO;
import com.EstoShoes.api.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
	private AuthService authService;

    @PostMapping(value = "/login")
	public ResponseEntity<?> login(@RequestBody AuthenticationDTO authDto){
		return ResponseEntity.ok(authService.login(authDto));
	}
}
