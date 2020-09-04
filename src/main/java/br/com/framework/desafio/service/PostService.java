package br.com.framework.desafio.service;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.framework.desafio.model.Post;
import br.com.framework.desafio.model.Usuario;
import br.com.framework.desafio.repository.PostRepository;
import br.com.framework.desafio.repository.UsuarioRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Post> listaTodos() {
		return postRepository.findAll();
	}
	
	public void salvarPost(Post post, Principal principal) {
		Usuario usuario = usuarioRepository.findByLogin(principal.getName());
		post.setUsuario(usuario);
		postRepository.save(post);
	}
	
	public void excluirPost(Long id, Principal principal) throws Exception {
		Usuario usuario = usuarioRepository.findByLogin(principal.getName());
		Post post = postRepository.findById(id).orElseThrow();
		
		if (post.getId().equals(usuario.getId())) {
			postRepository.deleteById(id);
		} else {
			 throw new Exception();
		}
	}
	
	public Post buscaPost(Long id, Principal principal) throws Exception {
		Usuario usuario = usuarioRepository.findByLogin(principal.getName());
		Post post = postRepository.findById(id).orElseThrow();
		
		if (post.getId().equals(usuario.getId())) {
			return post;
		} else {
			 throw new Exception();
		}
		
	}

	public List<Post> listaPostsUsuario(Principal principal) {
		Usuario usuario = usuarioRepository.findByLogin(principal.getName());
		return postRepository.findByUsuario(usuario);
	}
}
