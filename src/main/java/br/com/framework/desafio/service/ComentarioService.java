package br.com.framework.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.framework.desafio.model.Comentario;
import br.com.framework.desafio.repository.ComentarioRepository;

@Service
public class ComentarioService {
	
	@Autowired
	private ComentarioRepository comentarioRepository;

	public List<Comentario> listaTodos() {
		return comentarioRepository.findAll();
	}
	
	public Comentario comentario(Long id) {
		return comentarioRepository.findById(id).orElseThrow();
	}
	
	public void salvaComentario(Comentario comentario) {
		comentarioRepository.save(comentario);
	}
	
	public Comentario buscaUsuario(Long id) {
		return null;
	}
}
