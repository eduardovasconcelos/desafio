package br.com.framework.desafio.model.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.framework.desafio.model.Foto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumDTO {

	private Long id;
	private String nome;
	private String dono;
	private List<Foto> fotos;
	private MultipartFile[] arquivos;
}
