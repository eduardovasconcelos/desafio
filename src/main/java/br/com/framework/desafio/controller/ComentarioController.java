package br.com.framework.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.framework.desafio.model.dto.ComentarioDTO;
import br.com.framework.desafio.service.ComentarioService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class ComentarioController {
	
	@Autowired
	private ComentarioService comentarioService;
	
	@DeleteMapping(value = "/comentario/{id}")
	@ResponseBody
	public ResponseEntity<?> excluirComentario(@PathVariable(required = true) Long id) throws Exception {
		comentarioService.excluirComentario(id);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping(value = "/comentario")
	@ResponseBody
	public ResponseEntity<?> salvarComentario(@RequestBody ComentarioDTO comentario) {
		comentarioService.salvarComentario(comentario);
		return ResponseEntity.ok().build();
	}

}
