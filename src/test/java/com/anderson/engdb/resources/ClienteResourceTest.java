package com.anderson.engdb.resources;

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

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK, 
		classes = EngdbApplication.class)
@AutoConfigureMockMvc
public class ClienteResourceTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void givenClienteId_whenGetCliente_thenStatus200() throws Exception {
		
		mvc.perform(get("/clientes/4")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content()
			.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.cpf").value("35838452340"));	
	}
	
	@Test
	public void givenClienteCpf_whenGetCliente_thenStatus200() throws Exception {
		
		mvc.perform(get("/clientes/cpf?value=94542917690")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
				.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.nome").value("Levi Leonardo Sebastião Gonçalves"));
	}
	
	@Test
	public void givenClienteIdNotExist_whenGetCliente_thenStatus404() throws Exception {
		
		mvc.perform(get("/clientes/9999")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());	
	}
	
	@Test
	public void givenClienteCpfNotExist_whenGetCliente_thenStatus404() throws Exception {
		
		mvc.perform(get("/clientes/cpf?value=88153912402")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
}
