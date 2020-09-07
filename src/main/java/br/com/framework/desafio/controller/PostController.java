package br.com.framework.desafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.framework.desafio.model.Post;
import br.com.framework.desafio.model.dto.PostDTO;
import br.com.framework.desafio.service.PostService;

@RestController
@CrossOrigin(origins = "*")
public class PostController {
	
	@Autowired
	private PostService postService;

	@GetMapping(value = "/posts")
	@ResponseBody
	public List<Post> posts() {
		return postService.listaTodos();
	}
	
	@GetMapping(value = "/posts/usuario")
	@ResponseBody
	public List<Post> postsUsuario() {
		return postService.listaPostsUsuario();
	}
	
	@GetMapping(value = "/post/{id}")
	@ResponseBody
	public Post buscaPost(@PathVariable(required = true) Long id) throws Exception {
		return postService.buscaPost(id);
	}
	
	@PostMapping(value = "/post")
	public void salvarPost(@RequestBody PostDTO postDTO) {
		postService.salvarPost(postDTO);
	}
	
	@DeleteMapping(value = "post/{id}")
	public void excluirPost(@PathVariable(required = true) Long id) throws Exception {
		postService.excluirPost(id);
	}
}
