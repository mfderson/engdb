package com.anderson.engdb.resources;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.anderson.engdb.EngdbApplication;
import com.anderson.engdb.domain.Vendedor;
import com.anderson.engdb.repositories.VendedorRepository;
import com.anderson.engdb.services.VendedorService;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK, 
		classes = EngdbApplication.class)
@AutoConfigureMockMvc
public class VendedorResourceTest {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private VendedorRepository repository;
	
	@Autowired
	private VendedorService service;
	
	@Test
	public void givenVendedorId_whenGetVendedor_thenStatus200() throws Exception {
		
		Vendedor vendGuilherme = new Vendedor(null, "Guilherme Victor Thiago Corte Real", "09720484306");
		repository.save(vendGuilherme);
		
		mvc.perform(get("/vendedores/" + vendGuilherme.getId())
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content()
			.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.cpf", is("09720484306")));	
	}
	
	@Test
	public void givenVendedorCpf_whenGetVendedor_thenStatus200() throws Exception {
		
		Vendedor vendIsabella = new Vendedor(null, "Isabella Maria Novaes", "06558847264");
		repository.save(vendIsabella);
		
		mvc.perform(get("/vendedores/?cpf=" + vendIsabella.getCpf())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.cpf", is("06558847264")));
	}
}
