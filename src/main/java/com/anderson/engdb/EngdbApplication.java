package com.anderson.engdb;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.anderson.engdb.domain.Vendedor;
import com.anderson.engdb.repositories.VendedorRepository;

@SpringBootApplication
public class EngdbApplication implements CommandLineRunner {

	@Autowired
	private VendedorRepository vendedorRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(EngdbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Vendedor vend1 = new Vendedor(null, "Guilherme Victor Thiago Corte Real", "09720484306");
		Vendedor vend2 = new Vendedor(null, "Isabella Maria Novaes", "06558847264");
		Vendedor vend3 = new Vendedor(null, "Sara Isabel Nicole Moraes", "54591592375");
		
		vendedorRepository.saveAll(Arrays.asList(vend1, vend2, vend3));
		
	}

}
