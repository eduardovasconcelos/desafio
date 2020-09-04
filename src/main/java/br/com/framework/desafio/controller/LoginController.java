package br.com.framework.desafio.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.framework.desafio.model.dto.UsuarioDTO;

@RestController
public class LoginController {
	
	@PostMapping
	public void login(@RequestBody UsuarioDTO usuarioDTO, HttpServletResponse response) {
		
		
	}
}
