package com.anderson.engdb.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anderson.engdb.domain.Cliente;
import com.anderson.engdb.domain.Vendedor;
import com.anderson.engdb.domain.enums.Sexo;
import com.anderson.engdb.repositories.ClienteRepository;
import com.anderson.engdb.repositories.VendedorRepository;

@Service
public class DBService {

	@Autowired
	private VendedorRepository vendedorRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public void instantiateTestDatabase() {
		Vendedor vend1 = new Vendedor(null, "Guilherme Victor Thiago Corte Real", "09720484306");
		Vendedor vend2 = new Vendedor(null, "Isabella Maria Novaes", "06558847264");
		Vendedor vend3 = new Vendedor(null, "Sara Isabel Nicole Moraes", "54591592375");
		Vendedor vend4  = new Vendedor(null, "Carlos Eduardo Antonio Manuel Sales", "89396039748");
		Vendedor vend5  = new Vendedor(null, "Diego Arthur Lopes", "85200640512");
		Vendedor vend6  = new Vendedor(null, "Kaique Pedro Henrique Vieira", "50860383164");
		Vendedor vend7  = new Vendedor(null, "Benedito Levi Aragão", "91428127470");
		Vendedor vend8  = new Vendedor(null, "Augusto Thales Gabriel Corte Real", "78155234630");
		Vendedor vend9  = new Vendedor(null, "Lucca Ruan Jesus", "65173740191");
		Vendedor vend10 = new Vendedor(null, "Vera Luiza Ramos", "43221094116");
		Vendedor vend11 = new Vendedor(null, "Antonella Raquel Teixeira", "22533992135");
		Vendedor vend12 = new Vendedor(null, "Rosa Adriana Milena Nunes", "93100887700");
		Vendedor vend13 = new Vendedor(null, "Clara Allana Analu Carvalho", "47079728452");
		Vendedor vend14 = new Vendedor(null, "Maya Kamilly da Cunha", "19323056880");
		Vendedor vend15 = new Vendedor(null, "Alana Mariah Sophia da Mata", "83076765841");
		Vendedor vend16 = new Vendedor(null, "Laís Elaine da Conceição", "87420870748");
		Vendedor vend17 = new Vendedor(null, "Elisa Stella Sophia Fernandes", "13116644594");
		Vendedor vend18 = new Vendedor(null, "Luna Márcia Aurora Moura", "08617643315");
		Vendedor vend19 = new Vendedor(null, "Silvana Kamilly Rosângela Ramos", "06343942580");
		Vendedor vend20 = new Vendedor(null, "Osvaldo Renato Aparício", "05609410568");
		Vendedor vend21 = new Vendedor(null, "Thales José Costa", "72240341181");
		Vendedor vend22 = new Vendedor(null, "Vicente Enrico Souza", "03994619815");
		Vendedor vend23 = new Vendedor(null, "Antonio Manuel Danilo Sales", "89069099772");
		Vendedor vend24 = new Vendedor(null, "Fábio Geraldo Luís Araújo", "77901432128");
		Vendedor vend25 = new Vendedor(null, "Julio Joaquim Caleb Nunes", "87369875571");
		Vendedor vend26 = new Vendedor(null, "Pedro Noah Drumond", "52606595114");
		
		
		vendedorRepository.saveAll(Arrays.asList(vend1, vend2, vend3, vend4, vend5, vend6, vend7, vend8, vend9, vend10,
				vend11, vend12, vend13, vend14, vend15, vend16, vend17, vend18, vend19, vend20, vend21, vend22, vend23, 
				vend24, vend25, vend26));
		
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
		
		clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5, cli6, cli7));
	}
}
