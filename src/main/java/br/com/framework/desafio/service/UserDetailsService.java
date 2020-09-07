package br.com.framework.desafio.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.framework.desafio.model.UserPrinciple;
import br.com.framework.desafio.model.Usuario;
import br.com.framework.desafio.repository.UsuarioRepository;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = usuarioRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));
		 
		return UserPrinciple.build(user);
	}
}
