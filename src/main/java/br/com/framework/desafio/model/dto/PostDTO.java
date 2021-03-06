package br.com.framework.desafio.model.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
	private Long id;
	private String titulo;
	private String texto;
	private String link;
	private String dono;
	private byte[] imagem;
	private List<ComentarioDTO> comentarios;
}
