package br.com.framework.desafio.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.framework.desafio.model.Comentario;

@Repository
public interface ComentarioRepository extends CrudRepository<Comentario, Long> {

}
