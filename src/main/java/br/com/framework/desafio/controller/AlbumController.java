package br.com.framework.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.framework.desafio.model.Album;
import br.com.framework.desafio.service.AlbumService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AlbumController {

	@Autowired
	private AlbumService albumService;
	
	@GetMapping(value = "albums")
	public List<Album> albums() {
		return albumService.buscaTodos();
	}
}
