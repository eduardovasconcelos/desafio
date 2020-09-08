package br.com.framework.desafio.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioDTO {

	private Long id;
	private String conteudo;
	private String dono;
	private Long idPost;
}
