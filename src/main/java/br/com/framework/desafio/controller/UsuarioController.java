package br.com.framework.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.framework.desafio.model.Usuario;
import br.com.framework.desafio.service.UsuarioService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
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
	public Usuario usuario(@PathVariable Long id) {
		return usuarioService.buscaUsuario(id);
	}
	
	@PostMapping(value = "/usuario")
	@ResponseBody
	public ResponseEntity<Void> salvarUsuario(@RequestBody Usuario usuario) {
		usuarioService.salvarUsuario(usuario);
		return ResponseEntity.noContent().build();
	}
}
