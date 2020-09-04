package br.com.framework.desafio.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.framework.desafio.model.Album;
import br.com.framework.desafio.model.Usuario;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {

	List<Album> findAll();

	List<Album> findByUsuario(Usuario usuario);
}
