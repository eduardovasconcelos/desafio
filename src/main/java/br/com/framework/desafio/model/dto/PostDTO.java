package br.com.framework.desafio.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
	private String titulo;
	private String texto;
	private String link;
	private String imagem;
}
