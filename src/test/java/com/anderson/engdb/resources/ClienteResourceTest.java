package com.anderson.engdb.resources;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.anderson.engdb.dto.ClienteNewDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	@Test
	public void givenClienteNewDtoValid_whenPostCliente_thenStatus201() throws Exception {
		
		ClienteNewDTO objDto = new ClienteNewDTO();
		objDto.setNome("Benjamin Tiago Renan da Silva");
		objDto.setCpf("68786078925");
		objDto.setSexo(1);
		objDto.setVendedorId(2);
		
		ObjectMapper objMapper = new ObjectMapper();
		String json = objMapper.writeValueAsString(objDto);
		
		mvc.perform(post("/clientes")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void givenClienteNewDtoInvalidName_whenPostCliente_thenStatus400() throws Exception {
		
		ClienteNewDTO objDto = new ClienteNewDTO();
		objDto.setNome("Ben");
		objDto.setCpf("01077656670");
		objDto.setSexo(1);
		objDto.setVendedorId(2);
		
		ObjectMapper objMapper = new ObjectMapper();
		String json = objMapper.writeValueAsString(objDto);
		
		mvc.perform(post("/clientes")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.msg").value("Erro de validação"))
				.andExpect(content().json("{'errors':[{'fieldName':'nome'}]}"));
	}
	
	@Test
	public void givenClienteNewDtoInvalidCpf_whenPostCliente_thenStatus400() throws Exception {
		
		ClienteNewDTO objDto = new ClienteNewDTO();
		objDto.setNome("Heitor Theo da Mata");
		objDto.setCpf("01077656671");
		objDto.setSexo(1);
		objDto.setVendedorId(2);
		
		ObjectMapper objMapper = new ObjectMapper();
		String json = objMapper.writeValueAsString(objDto);
		
		mvc.perform(post("/clientes")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.msg").value("Erro de validação"))
				.andExpect(content().json("{'errors':[{'fieldName':'cpf'}]}"));
	}
	
	@Test
	public void givenClienteNewDtoInvalidVendedorId_whenPostCliente_thenStatus404() throws Exception {
		
		ClienteNewDTO objDto = new ClienteNewDTO();
		objDto.setNome("Heitor Theo da Mata");
		objDto.setCpf("01077656670");
		objDto.setSexo(1);
		objDto.setVendedorId(9999);
		
		ObjectMapper objMapper = new ObjectMapper();
		String json = objMapper.writeValueAsString(objDto);
		
		mvc.perform(post("/clientes")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void givenClienteNewDtoInvalidSexo_whenPostCliente_thenStatus400() throws Exception {
		
		ClienteNewDTO objDto = new ClienteNewDTO();
		objDto.setNome("Heitor Theo da Mata");
		objDto.setCpf("01077656670");
		objDto.setSexo(4);
		objDto.setVendedorId(3);
		
		ObjectMapper objMapper = new ObjectMapper();
		String json = objMapper.writeValueAsString(objDto);
		
		mvc.perform(post("/clientes")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isBadRequest());
	}
}