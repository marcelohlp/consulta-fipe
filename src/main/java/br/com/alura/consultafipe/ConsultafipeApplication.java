package br.com.alura.consultafipe;

import br.com.alura.consultafipe.principal.MenuBusca;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsultafipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ConsultafipeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		MenuBusca menuBusca = new MenuBusca();

		menuBusca.exibirMenu();

	}
}
