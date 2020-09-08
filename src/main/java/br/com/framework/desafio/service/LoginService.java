package br.com.framework.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.framework.desafio.component.JwtProvider;
import br.com.framework.desafio.model.JwtResponse;
import br.com.framework.desafio.model.UserPrinciple;
import br.com.framework.desafio.model.Usuario;
import br.com.framework.desafio.model.dto.UsuarioDTO;
import br.com.framework.desafio.repository.UsuarioRepository;

@Service
public class LoginService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public ResponseEntity<?> autenticaUsuario(UsuarioDTO usuarioDTO) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(usuarioDTO.getUsername(), usuarioDTO.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), String.valueOf(userDetails.getAdmin())));
	}

	public ResponseEntity<?> registraUsuario(UsuarioDTO usuarioDTO) {
		
		if (usuarioRepository.existsByUsername(usuarioDTO.getUsername())) {
	      return new ResponseEntity<>("Username não está disponível",
	          HttpStatus.BAD_REQUEST);
	    }

		Usuario user = new Usuario(usuarioDTO.getNome(),
				usuarioDTO.getUsername(), 
				encoder.encode(usuarioDTO.getPassword()),
				usuarioDTO.getAdmin());

		usuarioRepository.save(user);

		return ResponseEntity.ok().build();
	}
}
