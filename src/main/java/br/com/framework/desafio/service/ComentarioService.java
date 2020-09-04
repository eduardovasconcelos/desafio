package br.com.framework.desafio.service;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.framework.desafio.model.Comentario;
import br.com.framework.desafio.model.Usuario;
import br.com.framework.desafio.repository.ComentarioRepository;
import br.com.framework.desafio.repository.UsuarioRepository;

@Service
public class ComentarioService {
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Comentario> listaTodos() {
		return comentarioRepository.findAll();
	}
	
	public List<Comentario> listaComentariosUsuario(Principal principal) {
		Usuario usuario = usuarioRepository.findByLogin(principal.getName());
		return comentarioRepository.findByUsuario(usuario);
	}
	
	public void salvaComentario(Comentario comentario) {
		comentarioRepository.save(comentario);
	}
	
	public Comentario comentario(Long id, Principal principal) throws Exception {
		Usuario usuario = usuarioRepository.findByLogin(principal.getName());
		Comentario comentario = comentarioRepository.findById(id).orElseThrow();
		
		if (comentario.getId().equals(usuario.getId())) {
			return comentario;
		} else {
			 throw new Exception();
		}
	}
	
	
	public void deletaComentario(Long id, Principal principal) throws Exception {
		Usuario usuario = usuarioRepository.findByLogin(principal.getName());
		Comentario comentario = comentarioRepository.findById(id).orElseThrow();
		
		if (comentario.getId().equals(usuario.getId())) {
			comentarioRepository.delete(comentario);
		} else {
			 throw new Exception();
		}
	}
}
