package br.com.framework.desafio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.framework.desafio.model.Usuario;
import br.com.framework.desafio.repository.UsuarioRepository;

@SpringBootApplication
@EntityScan("br.com.framework.desafio.model")
public class DesafioApplication {
	
	@Autowired
	PasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(DesafioApplication.class, args);
	}
	
	@Bean
    CommandLineRunner init(UsuarioRepository userRepository) {
        return args -> {
            Usuario user = new Usuario();
            user.setNome("Eduardo de Oliveira Vasconcelos");
            user.setUsername("eduardo");
            user.setPassword(encoder.encode("testes"));
            user.setAdmin(Boolean.TRUE);
            userRepository.save(user);
            
            Usuario user2 = new Usuario();
            user2.setNome("Poliana Alcantara");
            user2.setUsername("poliana");
            user2.setPassword(encoder.encode("poliana"));
            user2.setAdmin(Boolean.FALSE);
            userRepository.save(user2);
        };
    }
}
