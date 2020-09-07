package br.com.framework.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.framework.desafio.model.Comentario;
import br.com.framework.desafio.service.ComentarioService;

@RestController
@CrossOrigin(origins = "*")
public class ComentarioController {
	
	@Autowired
	private ComentarioService comentarioService;
	
	@DeleteMapping(value = "/comentario/{id}")
	public void excluirComentario(@PathVariable(required = true) Long id) throws Exception {
		comentarioService.excluirComentario(id);
	}
	
	@PostMapping(value = "/comentario")
	public void salvarComentario(@RequestBody Comentario comentario) {
		comentarioService.salvarComentario(comentario);
	}

}
