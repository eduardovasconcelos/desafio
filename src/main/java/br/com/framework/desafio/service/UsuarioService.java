package br.com.framework.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.framework.desafio.model.Usuario;
import br.com.framework.desafio.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Usuario> listaTodos() {
		return usuarioRepository.findAll();
	}
	
	public Usuario usuario(Long id) {
		return usuarioRepository.findById(id).orElseThrow();
	}
	
	public void salvaUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
	public Usuario buscaUsuario(Long id) {
		return null;
	}

}
