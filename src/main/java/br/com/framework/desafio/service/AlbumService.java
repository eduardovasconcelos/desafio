package br.com.framework.desafio.service;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.framework.desafio.model.Album;
import br.com.framework.desafio.model.Usuario;
import br.com.framework.desafio.repository.AlbumRepository;
import br.com.framework.desafio.repository.UsuarioRepository;

@Service
public class AlbumService {
	
	@Autowired
	private AlbumRepository albumRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Album> buscaTodos() {
		return albumRepository.findAll();
	}
	
	public void salvarAlbum(Album album, Principal principal) {
		Usuario usuario = usuarioRepository.findByLogin(principal.getName());
		album.setUsuario(usuario);
		albumRepository.save(album);
	}
	
	public void excluirAlbum(Long id, Principal principal) throws Exception {
		Usuario usuario = usuarioRepository.findByLogin(principal.getName());
		Album album = albumRepository.findById(id).orElseThrow();
		
		if (album.getId().equals(usuario.getId())) {
			albumRepository.deleteById(id);
		} else {
			 throw new Exception();
		}
	}
	
	public List<Album> buscaAlbumUsuario(Principal principal) {
		Usuario usuario = usuarioRepository.findByLogin(principal.getName());
		return albumRepository.findByUsuario(usuario);
	}

}
