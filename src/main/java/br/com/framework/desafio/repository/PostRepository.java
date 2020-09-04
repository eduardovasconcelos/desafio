package br.com.framework.desafio.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.framework.desafio.model.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Long>{
	
	List<Post> findAll();
}
