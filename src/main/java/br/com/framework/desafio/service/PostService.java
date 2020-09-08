package br.com.framework.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.framework.desafio.model.Post;
import br.com.framework.desafio.model.Usuario;
import br.com.framework.desafio.model.dto.PostDTO;
import br.com.framework.desafio.repository.PostRepository;
import br.com.framework.desafio.repository.UsuarioRepository;
import br.com.framework.desafio.utils.InformacaoUsuarioUtils;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Post> listaTodos() {
		return postRepository.findAll();
	}
	
	public void salvarPost(PostDTO postDTO) {
		Post post = new Post();
		Usuario usuario = usuarioRepository.findByUsername(InformacaoUsuarioUtils.getNameUser()).get();
		post.setUsuario(usuario);
		post.setLink(postDTO.getLink());
		post.setTexto(postDTO.getTexto());
		post.setTitulo(postDTO.getTitulo());
		post.setImagem(postDTO.getImagem().getBytes());
		postRepository.save(post);
	}
	
	public void excluirPost(Long id) throws Exception {
		Usuario usuario = usuarioRepository.findByUsername(InformacaoUsuarioUtils.getNameUser()).get();
		Post post = postRepository.findById(id).orElseThrow();
		
		if (post.getUsuario().getId().equals(usuario.getId())) {
			postRepository.deleteById(id);
		} else {
			 throw new Exception();
		}
	}
	
	public Post buscaPost(Long id) throws Exception {
		Usuario usuario = usuarioRepository.findByUsername(InformacaoUsuarioUtils.getNameUser()).get();
		Post post = postRepository.findById(id).orElseThrow();
		
		if (post.getUsuario().getId().equals(usuario.getId())) {
			return post;
		} else {
			 throw new Exception();
		}
		
	}

	public List<Post> listaPostsUsuario() {
		Usuario usuario = usuarioRepository.findByUsername(InformacaoUsuarioUtils.getNameUser()).get();
		return postRepository.findByUsuario(usuario);
	}
}
