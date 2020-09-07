package br.com.framework.desafio.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.framework.desafio.model.dto.UsuarioDTO;
import br.com.framework.desafio.service.LoginService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class LoginController {
	
	@Autowired
	private LoginService loginService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody UsuarioDTO usuarioDTO) {
		return loginService.autenticaUsuario(usuarioDTO);
	}
	
	@PostMapping("/registra")
	public ResponseEntity<?> registra(@Valid @RequestBody UsuarioDTO usuarioDTO) {
		return loginService.registraUsuario(usuarioDTO);
	}
}
