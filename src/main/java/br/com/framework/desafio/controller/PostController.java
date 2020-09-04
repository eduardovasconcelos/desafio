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

import br.com.framework.desafio.model.Post;
import br.com.framework.desafio.service.PostService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {
	
	@Autowired
	private PostService postService;

	@GetMapping
	public List<Post> posts() {
		return postService.listaTodos();
	}
	
	@GetMapping(value = "/posts/usuario")
	public List<Post> postsUsuario(Principal principal) {
		return postService.listaPostsUsuario(principal);
	}
	
	@GetMapping(value = "/post/{id}")
	public Post buscaPost(@PathVariable(required = true) Long id, Principal principal) throws Exception {
		return postService.buscaPost(id, principal);
	}
	
	@PostMapping(value = "/post")
	public void salvarPost(Post post, Principal principal) {
		postService.salvarPost(post, principal);
	}
	
	@DeleteMapping(value = "post/{id}")
	public void excluirPost(@PathVariable(required = true) Long id, Principal principal) throws Exception {
		postService.excluirPost(id, principal);
	}
}
