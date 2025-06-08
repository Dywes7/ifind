package br.edu.br.meuprimeirospringboot;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.br.meuprimeirospringboot.entity.Role;
import br.edu.br.meuprimeirospringboot.serviceImpl.UsuarioServiceImpl;


@SpringBootApplication
public class MeuprimeirospringbootApplication  implements CommandLineRunner {
	
	@Autowired
    private UsuarioServiceImpl usuario;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(MeuprimeirospringbootApplication.class, args);
	}
	

	@Override
	public void run(String... args) throws Exception {
		
		
		usuario.criarUsuario("admin", "admin", Role.ADMIN);
		usuario.criarUsuario("user", "user", Role.USER);
		
		
		
		
	}

}
