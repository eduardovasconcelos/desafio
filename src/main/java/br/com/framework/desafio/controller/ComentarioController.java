package br.com.framework.desafio.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.framework.desafio.model.Comentario;
import br.com.framework.desafio.service.ComentarioService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/comentario/")
public class ComentarioController {
	
	@Autowired
	private ComentarioService comentarioService;
	
	@DeleteMapping(value = "{id}")
	public void excluirComentario(@PathVariable(required = true) Long id, Principal principal) throws Exception {
		comentarioService.excluirComentario(id, principal);
	}
	
	@PostMapping(value = "")
	public void salvarComentario(@RequestBody Comentario comentario, Principal principal) {
		comentarioService.salvarComentario(comentario, principal);
	}

}
