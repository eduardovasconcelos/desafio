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

import br.com.framework.desafio.model.Post;
import br.com.framework.desafio.model.dto.PostDTO;
import br.com.framework.desafio.service.PostService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostService postService;

	@GetMapping(value = "/posts")
	@ResponseBody
	public List<PostDTO> posts() {
		return postService.listaTodos();
	}
	
	@GetMapping(value = "/postsPesquisa")
	@ResponseBody
	public List<PostDTO> postsPesquisa(@RequestParam("filtro") String filtro) {
		return postService.listaPostsPesquisa(filtro);
	}
	
	@GetMapping(value = "/posts/usuario")
	@ResponseBody
	public List<Post> postsUsuario() {
		return postService.listaPostsUsuario();
	}
	
	@GetMapping(value = "/post/{id}")
	@ResponseBody
	public PostDTO detalhePost(@PathVariable(required = true) Long id) throws Exception {
		return postService.buscaPost(id);
	}
	
	@PostMapping(value = "/post")
	public ResponseEntity<?> salvarPost(HttpServletRequest request,
			@RequestParam("texto") String texto,
			@RequestParam("arquivos") MultipartFile arquivo) throws IOException {
		postService.salvarPost(request, texto, arquivo);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(value = "post/{id}")
	public ResponseEntity<?> excluirPost(@PathVariable(required = true) Long id) throws Exception {
		postService.excluirPost(id);
		return ResponseEntity.ok().build();
	}
}
