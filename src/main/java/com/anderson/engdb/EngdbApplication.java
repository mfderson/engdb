package com.anderson.engdb;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.anderson.engdb.domain.Cliente;
import com.anderson.engdb.domain.Vendedor;
import com.anderson.engdb.domain.enums.Sexo;
import com.anderson.engdb.repositories.ClienteRepository;
import com.anderson.engdb.repositories.VendedorRepository;

@SpringBootApplication
public class EngdbApplication implements CommandLineRunner {

	@Autowired
	private VendedorRepository vendedorRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(EngdbApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Vendedor vend1 = new Vendedor(null, "Guilherme Victor Thiago Corte Real", "09720484306");
		Vendedor vend2 = new Vendedor(null, "Isabella Maria Novaes", "06558847264");
		Vendedor vend3 = new Vendedor(null, "Sara Isabel Nicole Moraes", "54591592375");
		
		Cliente cli1 = new Cliente(null, "Tomás Kauê José Cardoso", "35838452340", Sexo.MASCULINO, vend1);
		Cliente cli2 = new Cliente(null, "Levi Leonardo Sebastião Gonçalves", "94542917690", Sexo.MASCULINO, vend1);
		Cliente cli3 = new Cliente(null, "Luna Luzia Manuela dos Santos", "91020915692", Sexo.FEMININO, vend2);
		Cliente cli4 = new Cliente(null, "Stella Isis Galvão", "86081780449", Sexo.FEMININO, vend2);
		Cliente cli5 = new Cliente(null, "Bárbara Rebeca Gonçalves", "37909969805", Sexo.FEMININO, vend3);
		Cliente cli6 = new Cliente(null, "Mateus Leonardo Mendes", "13441206010", Sexo.MASCULINO, vend3);
		Cliente cli7 = new Cliente(null, "Roberto Arthur João Rocha", "34452633706", Sexo.MASCULINO, vend3);
		
		vend1.getClientes().addAll(Arrays.asList(cli1, cli2));
		vend2.getClientes().addAll(Arrays.asList(cli3, cli4));
		vend1.getClientes().addAll(Arrays.asList(cli5, cli6, cli7));
		
		vendedorRepository.saveAll(Arrays.asList(vend1, vend2, vend3));
		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5, cli6, cli7));
		
	}

}
