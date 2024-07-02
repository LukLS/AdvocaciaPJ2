package com.example.atv2Dac;

import com.example.atv2Dac.controller.AdvogadoController;
import com.example.atv2Dac.controller.AssociadoController;
import com.example.atv2Dac.controller.ClienteController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.atv2Dac.dao")
public class Atv2DacApplication implements CommandLineRunner {

	@Autowired
	private AdvogadoController advogadoController;

	@Autowired
	private AssociadoController associadoController;

	@Autowired
	private ClienteController clienteController;

	public static void main(String[] args) {
		SpringApplication.run(Atv2DacApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
