package br.com.framework.desafio.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
	
	private String nome;
	private String username;
	private String password;
	private Boolean admin;
}
