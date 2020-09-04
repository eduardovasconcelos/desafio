package br.com.framework.desafio.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.framework.desafio.model.Usuario;
import br.com.framework.desafio.service.UsuarioService;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	
	@GetMapping(value = "/usuarios")
	@ResponseBody
	public List<Usuario> listaTodos() {
		return usuarioService.listaTodos();
	}
	
	@GetMapping(value = "/usuario/{id}")
	@ResponseBody
	public Usuario usuario(@PathParam("id") Long id) {
		return usuarioService.listaTodos().get(0);
	}
	
	@PostMapping(value = "/usuario")
	@ResponseBody
	public ResponseEntity<Void> salvaUsuario(@RequestBody Usuario usuario) {
		usuarioService.listaTodos();
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/usuario/")
	@ResponseBody
	public Usuario buscaUsuario(@PathParam("id") Long id) {
		return usuarioService.listaTodos().get(0);
	}
}
