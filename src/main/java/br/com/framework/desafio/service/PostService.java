package br.com.framework.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.framework.desafio.model.Post;
import br.com.framework.desafio.model.Usuario;
import br.com.framework.desafio.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;

	public List<Post> listaTodos() {
		return postRepository.findAll();
	}
	
	public Post post(Long id) {
		return postRepository.findById(id).orElseThrow();
	}
	
	public void salvaPost(Post post) {
		postRepository.save(post);
	}
	
	public Usuario buscaPost(Long id) {
		return null;
	}
}
