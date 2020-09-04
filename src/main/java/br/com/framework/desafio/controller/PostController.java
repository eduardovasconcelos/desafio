package br.com.framework.desafio.controller;

import java.security.Principal;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.framework.desafio.model.Post;
import br.com.framework.desafio.service.PostService;

@RestController
public class PostController {
	
	@Autowired
	private PostService postService;

	@GetMapping
	public List<Post> posts(Principal principal) {
		return postService.listaPostsUsuario(principal);
	}
	
	@GetMapping(value = "/post/{id}")
	public Post buscaPost(@PathParam(value = "id") Long id, Principal principal) throws Exception {
		return postService.buscaPost(id, principal);
	}
}
