package br.com.framework.desafio.service;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.framework.desafio.model.Usuario;
import br.com.framework.desafio.model.dto.UsuarioDTO;
import br.com.framework.desafio.repository.UsuarioRepository;
import br.com.framework.desafio.utils.InformacaoUsuarioUtils;

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
		usuario.setAdmin(usuario.getAdmin() == null || Boolean.FALSE.equals(usuario.getAdmin()) ? false : true);
		usuario.setPassword(DigestUtils.sha1Hex(usuario.getPassword()));
		usuarioRepository.save(usuario);
	}

	public UsuarioDTO buscaUsuarioLogado() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null) {			
			Usuario usuario = usuarioRepository.findByUsername(InformacaoUsuarioUtils.getNameUser()).get();
			
			UsuarioDTO userDTO = new UsuarioDTO();
			userDTO.setNome(usuario.getUsername());
			userDTO.setAdmin(usuario.getAdmin());
			return userDTO;
		}
		return null;
	}
	
}
