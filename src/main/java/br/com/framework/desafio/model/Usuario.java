package br.com.framework.desafio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String username;
	private String password;
	private Boolean admin;
	
	public Usuario(String nome, String username, String password, Boolean admin) {
		this.nome = nome;
		this.username = username;
		this.password = password;
		this.admin = admin;
	}
}
