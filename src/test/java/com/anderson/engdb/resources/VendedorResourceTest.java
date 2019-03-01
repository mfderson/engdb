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
		
		Vendedor vend1 = new Vendedor(null, "Severino Cláudio Vitor da Cunha", "98231540156");
		repository.save(vend1);
		
		mvc.perform(get("/vendedores/" + vend1.getId())
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content()
			.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.cpf", is("98231540156")));	
	}
	
	@Test
	public void givenVendedorCpf_whenGetVendedor_thenStatus200() throws Exception {
		
		Vendedor vend = new Vendedor(null, "Juliana Andreia Evelyn Rodrigues", "00163681694");
		repository.save(vend);
		
		mvc.perform(get("/vendedores/cpf?value=" + vend.getCpf())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.cpf", is("00163681694")));
	}
	
	@Test
	public void givenVendedorIdNotExist_whenGetVendedor_thenStatus404() throws Exception {
		
		mvc.perform(get("/vendedores/9999")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());	
	}
	
	@Test
	public void givenVendedorCpfNotExist_whenGetVendedor_thenStatus404() throws Exception {
		
		mvc.perform(get("/vendedores/cpf?value=88153912402")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
}
