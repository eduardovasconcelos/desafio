package br.com.framework.desafio.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.framework.desafio.model.Album;
import br.com.framework.desafio.service.AlbumService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AlbumController {

	@Autowired
	private AlbumService albumService;
	
	@GetMapping(value = "/albums")
	public List<Album> albums() {
		return albumService.buscaTodos();
	}
	
	@PostMapping(value = "/album")
	public void salvarAlbum(Album album, Principal principal) {
		albumService.salvarAlbum(album, principal);
	}
	
	@DeleteMapping(value = "/album/{id}")
	public void excluirAlbum(@PathVariable(required = true) Long id, Principal principal) throws Exception {
		albumService.excluirAlbum(id, principal);
	}
}
