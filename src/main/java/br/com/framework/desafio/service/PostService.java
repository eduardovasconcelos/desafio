package br.com.framework.desafio.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.framework.desafio.model.Comentario;
import br.com.framework.desafio.model.Post;
import br.com.framework.desafio.model.Usuario;
import br.com.framework.desafio.model.dto.ComentarioDTO;
import br.com.framework.desafio.model.dto.PostDTO;
import br.com.framework.desafio.repository.ComentarioRepository;
import br.com.framework.desafio.repository.PostRepository;
import br.com.framework.desafio.repository.UsuarioRepository;
import br.com.framework.desafio.utils.InformacaoUsuarioUtils;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;

	public List<PostDTO> listaTodos() {
		return postRepository.findAll()
				.stream()
				.map(post -> {
					PostDTO dto = new PostDTO();
					dto.setId(post.getId());					
					dto.setDono(post.getUsuario().getUsername());
					dto.setImagem(post.getImagem());
					dto.setLink(post.getLink());
					dto.setTexto(post.getTexto());
					dto.setTitulo(post.getTitulo());
					
					return dto;
				}).collect(Collectors.toList());
	}
	
	public List<PostDTO> listaPostsPesquisa(String filtro) {
		return postRepository.findByTextoContainingAndLinkContaining(filtro, filtro)
				.stream()
				.map(post -> {
					PostDTO dto = new PostDTO();
					dto.setId(post.getId());
					dto.setDono(post.getUsuario().getUsername());
					dto.setImagem(post.getImagem());
					dto.setLink(post.getLink());
					dto.setTexto(post.getTexto());
					dto.setTitulo(post.getTitulo());
										
					return dto;
				}).collect(Collectors.toList());
	}
	
	public void salvarPost(PostDTO postDTO) {
		Post post = new Post();
		Usuario usuario = usuarioRepository.findByUsername(InformacaoUsuarioUtils.getNameUser()).get();
		post.setUsuario(usuario);
		post.setLink(postDTO.getLink());
		post.setTexto(postDTO.getTexto());
		post.setTitulo(postDTO.getTitulo());
		post.setImagem(postDTO.getImagem());
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
	
	public PostDTO buscaPost(Long id) throws Exception {
		Post post = postRepository.findById(id).orElseThrow();
		List<Comentario> coment = comentarioRepository.findByPost(post);
		List<ComentarioDTO> comentarios = coment
				.stream()
				.map(comentario -> {
					ComentarioDTO dto = new ComentarioDTO();
					dto.setConteudo(comentario.getTexto());
					dto.setId(comentario.getId());
					dto.setDono(comentario.getUsuario().getUsername());
					return dto;
				}).collect(Collectors.toList());
		PostDTO dto = new PostDTO(post.getId(),
				post.getTitulo(),
				post.getTexto(),
				post.getLink(),
				post.getUsuario().getUsername(),
				post.getImagem(),
				comentarios);
		
		return dto;
	}

	public List<Post> listaPostsUsuario() {
		Usuario usuario = usuarioRepository.findByUsername(InformacaoUsuarioUtils.getNameUser()).get();
		return postRepository.findByUsuario(usuario);
	}

	public void salvarPost(HttpServletRequest request, String texto, MultipartFile arquivo) throws IOException {
		Post post = new Post();
		Usuario usuario = usuarioRepository.findByUsername(InformacaoUsuarioUtils.getNameUser()).get();
		post.setUsuario(usuario);
		post.setLink(request.getParameter("link"));
		post.setTexto(texto);
		post.setTitulo(request.getParameter("titulo"));
		post.setImagem(arquivo.getBytes());
		postRepository.save(post);
		
	}
}
