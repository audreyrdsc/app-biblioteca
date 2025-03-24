package com.unifap.biblioteca;

import com.unifap.biblioteca.entities.Livro;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BibliotecaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//System.out.println("\nSenha 123: " + new BCryptPasswordEncoder().encode("123"));
		//System.out.println("\n### Mensagem: Aplicação em funcionamento... ###\n");
	}
}
