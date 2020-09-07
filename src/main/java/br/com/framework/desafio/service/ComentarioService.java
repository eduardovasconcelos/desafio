package br.com.framework.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.framework.desafio.model.Comentario;
import br.com.framework.desafio.model.Usuario;
import br.com.framework.desafio.repository.ComentarioRepository;
import br.com.framework.desafio.repository.UsuarioRepository;
import br.com.framework.desafio.utils.InformacaoUsuarioUtils;

@Service
public class ComentarioService {
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Comentario> listaTodos() {
		return comentarioRepository.findAll();
	}
	
	public List<Comentario> listaComentariosUsuario() {
		Usuario usuario = usuarioRepository.findByUsername(InformacaoUsuarioUtils.getNameUser()).get();
		return comentarioRepository.findByUsuario(usuario);
	}
	
	public void salvarComentario(Comentario comentario) {
		Usuario usuario = usuarioRepository.findByUsername(InformacaoUsuarioUtils.getNameUser()).get();
		comentario.setUsuario(usuario);
		comentarioRepository.save(comentario);
	}
	
	public Comentario comentario(Long id) throws Exception {
		Usuario usuario = usuarioRepository.findByUsername(InformacaoUsuarioUtils.getNameUser()).get();
		Comentario comentario = comentarioRepository.findById(id).orElseThrow();
		
		if (comentario.getId().equals(usuario.getId())) {
			return comentario;
		} else {
			 throw new Exception();
		}
	}
	
	
	public void excluirComentario(Long id) throws Exception {
		Usuario usuario = usuarioRepository.findByUsername(InformacaoUsuarioUtils.getNameUser()).get();
		Comentario comentario = comentarioRepository.findById(id).orElseThrow();
		
		if (comentario.getId().equals(usuario.getId())) {
			comentarioRepository.delete(comentario);
		} else {
			 throw new Exception();
		}
	}
}
