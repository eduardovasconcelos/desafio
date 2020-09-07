package br.com.framework.desafio.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.framework.desafio.model.Album;
import br.com.framework.desafio.model.Usuario;
import br.com.framework.desafio.model.dto.AlbumDTO;
import br.com.framework.desafio.repository.AlbumRepository;
import br.com.framework.desafio.repository.UsuarioRepository;
import br.com.framework.desafio.utils.InformacaoUsuarioUtils;

@Service
public class AlbumService {
	
	@Autowired
	private AlbumRepository albumRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Album> buscaTodos() {
		return albumRepository.findAll();
	}
	
	public void salvarAlbum(AlbumDTO albumDTO) {
		Album album = new Album();
		Usuario usuario = usuarioRepository.findByUsername(InformacaoUsuarioUtils.getNameUser()).get();
		album.setUsuario(usuario);
		album.setNome(albumDTO.getNome());
		albumRepository.save(album);
	}
	
	public void excluirAlbum(Long id) throws Exception {
		Usuario usuario = usuarioRepository.findByUsername(InformacaoUsuarioUtils.getNameUser()).get();
		Album album = albumRepository.findById(id).orElseThrow();
		
		if (album.getId().equals(usuario.getId())) {
			albumRepository.deleteById(id);
		} else {
			 throw new Exception();
		}
	}
	
	public List<Album> buscaAlbumUsuario() {
		Usuario usuario = usuarioRepository.findByUsername(InformacaoUsuarioUtils.getNameUser()).get();
		return albumRepository.findByUsuario(usuario);
	}

	public void salvarAlbum(HttpServletRequest request, MultipartFile[] arquivos) {
		Album album = new Album();
		Usuario usuario = usuarioRepository.findByUsername(InformacaoUsuarioUtils.getNameUser()).get();
		album.setUsuario(usuario);
		album.setNome(request.getParameter("nome"));
		albumRepository.save(album);
		
	}

}
