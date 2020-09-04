package br.com.framework.desafio;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import br.com.framework.desafio.model.Usuario;
import br.com.framework.desafio.repository.UsuarioRepository;

@SpringBootApplication
@EntityScan("br.com.framework.desafio.model")
public class DesafioApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
	}
	
	@Bean
    CommandLineRunner init(UsuarioRepository userRepository) {
        return args -> {
            Usuario user = new Usuario();
            user.setLogin("eduardo");
            user.setPassword(DigestUtils.sha1Hex("desafio"));
            userRepository.save(user);
        };
    }
}
