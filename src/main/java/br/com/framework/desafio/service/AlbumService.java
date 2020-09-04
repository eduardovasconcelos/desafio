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
	
	public void salvaAlbum(Album album) {
		albumRepository.save(album);
	}
	
	public void apagaAlbum(Long id) {
		albumRepository.deleteById(id);
	}
	
	public List<Album> buscaAlbumUsuario(Principal principal) {
		Usuario usuario = usuarioRepository.findByLogin(principal.getName());
		return albumRepository.findByUsuario(usuario);
	}

}
