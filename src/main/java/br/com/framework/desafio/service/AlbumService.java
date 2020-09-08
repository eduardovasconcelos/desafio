package br.com.framework.desafio.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.framework.desafio.model.Album;
import br.com.framework.desafio.model.Foto;
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

	public List<AlbumDTO> buscaTodos() {
		return albumRepository.findAll()
				.stream()
				.map(album -> {
					AlbumDTO dto = new AlbumDTO();
					dto.setId(album.getId());
					dto.setDono(album.getUsuario().getUsername());
					dto.setFotos(album.getFotos());
					dto.setNome(album.getNome());
					return dto;
				}).collect(Collectors.toList());
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
		
		if (album.getUsuario().getId().equals(usuario.getId())) {
			albumRepository.deleteById(id);
		} else {
			 throw new Exception();
		}
	}
	
	public List<Album> buscaAlbumUsuario() {
		Usuario usuario = usuarioRepository.findByUsername(InformacaoUsuarioUtils.getNameUser()).get();
		return albumRepository.findByUsuario(usuario);
	}

	public void salvarAlbum(HttpServletRequest request, MultipartFile[] arquivos) throws IOException {
		Album album = new Album();
		Usuario usuario = usuarioRepository.findByUsername(InformacaoUsuarioUtils.getNameUser()).get();
		album.setUsuario(usuario);
		album.setNome(request.getParameter("nome"));
		
		List<Foto> fotos = new ArrayList<>();
		
		for (MultipartFile arquivo : arquivos) {
			Foto foto = new Foto();
			foto.setArquivo(arquivo.getBytes());
			fotos.add(foto);
		}
		
		album.setFotos(fotos);
		albumRepository.save(album);
		
	}

	public AlbumDTO abrirAlbum(Long id) {
		Album album =  albumRepository.findById(id).orElseThrow();			
		AlbumDTO dto = new AlbumDTO();
		dto.setId(album.getId());
		dto.setDono(album.getUsuario().getUsername());
		dto.setFotos(album.getFotos());
		dto.setNome(album.getNome());
		return dto;
	}

}
