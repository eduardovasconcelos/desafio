package br.com.framework.desafio.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.framework.desafio.model.Comentario;
import br.com.framework.desafio.model.Post;
import br.com.framework.desafio.model.Usuario;

@Repository
public interface ComentarioRepository extends CrudRepository<Comentario, Long> {

	List<Comentario> findAll();
	
	List<Comentario> findByPost(Post post);
	
	List<Comentario> findByUsuario(Usuario usuario);
}
