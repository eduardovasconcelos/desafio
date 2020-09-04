package br.com.framework.desafio.service;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
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
	
	public Usuario buscaUsuario(Long id) {
		return usuarioRepository.findById(id).orElseThrow();
	}
	
	public void salvarUsuario(Usuario usuario) {
		usuario.setPassword(DigestUtils.sha1Hex(usuario.getPassword()));
		usuarioRepository.save(usuario);
	}
	
}
