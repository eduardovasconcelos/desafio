package br.com.framework.desafio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

	private String token;
	private String type = "Bearer";
	private String username;
	private String admin;

	public JwtResponse(String accessToken, String username, String admin) {
		this.token = accessToken;
		this.username = username;
		this.admin = admin;
	}
}