package br.com.framework.desafio.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.framework.desafio.model.dto.AlbumDTO;
import br.com.framework.desafio.service.AlbumService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class AlbumController {

	@Autowired
	private AlbumService albumService;
	
	@GetMapping(value = "/albums")
	@ResponseBody
	public List<AlbumDTO> albums() {
		return albumService.buscaTodos();
	}
	
	@PostMapping(value = "/album")
	@ResponseBody
	public ResponseEntity<Void> salvarAlbum(HttpServletRequest request, @RequestParam("arquivos") MultipartFile[] arquivos) throws IOException {
		albumService.salvarAlbum(request, arquivos);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(value = "/album/{id}")
	@ResponseBody
	public ResponseEntity<Void> excluirAlbum(@PathVariable(required = true) Long id) throws Exception {
		albumService.excluirAlbum(id);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value = "/album/{id}")
	@ResponseBody
	public AlbumDTO abrirAlbum(@PathVariable(required = true) Long id) {
		return albumService.abrirAlbum(id);
	}
}
