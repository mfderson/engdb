package com.anderson.engdb.resources;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import com.anderson.engdb.dto.ClienteNewDTO;
import com.anderson.engdb.dto.VendedorNewDTO;
import com.anderson.engdb.dto.VendedorUpdtDTO;
import com.anderson.engdb.repositories.VendedorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	//@Autowired
	//private VendedorService service;
	
	@Test
	public void givenVendedorId_whenGetVendedor_thenStatus200() throws Exception {
		
		Vendedor vend1 = new Vendedor(null, "Severino Cláudio Vitor da Cunha", "98231540156");
		repository.save(vend1);
		
		mvc.perform(get("/vendedores/" + vend1.getId())
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content()
			.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.cpf").value("98231540156"));	
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
				.andExpect(jsonPath("$.cpf").value("00163681694"));
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
	
	@Test
	public void givenVendedorNewDtoValid_whenPostVendedor_thenStatus201() throws Exception {
		
		VendedorNewDTO vend = new VendedorNewDTO();
		vend.setNome("Anthony Juan Ricardo Brito");
		vend.setCpf("82822436398");
		
		ObjectMapper objMapper = new ObjectMapper();
		String json = objMapper.writeValueAsString(vend);
		
		mvc.perform(post("/vendedores")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void givenVendedorNewDtoInvalidName_whenPostVendedor_thenStatus422() throws Exception {
		
		VendedorNewDTO vend = new VendedorNewDTO();
		vend.setNome("Anth");
		vend.setCpf("85265309250");
		
		ObjectMapper objMapper = new ObjectMapper();
		String json = objMapper.writeValueAsString(vend);
		
		mvc.perform(post("/vendedores")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isUnprocessableEntity())
				.andExpect(content().json("{'errors':[{'fieldName':'nome'}]}"));
	}
	
	@Test
	public void givenVendedorNewDtoInvalidCpf_whenPostVendedor_thenStatus400() throws Exception {
		
		VendedorNewDTO vend = new VendedorNewDTO();
		vend.setNome("Heitor Theo da Mata");
		vend.setCpf("85265309251");
		
		ObjectMapper objMapper = new ObjectMapper();
		String json = objMapper.writeValueAsString(vend);
		
		mvc.perform(post("/vendedores")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isUnprocessableEntity())
				.andExpect(content().json("{'errors':[{'fieldName':'cpf'}]}"));
	}
	
	@Test
	public void givenVendedorNewDtoInvalidCpfAndName_whenPostVendedor_thenStatus422() throws Exception {
		
		VendedorNewDTO vend = new VendedorNewDTO();
		vend.setNome("Agatha Kamilly Alessandra Campos Amaral Santos Aparecida Moura Santana Lisboa");
		vend.setCpf("76724559668");
		
		ObjectMapper objMapper = new ObjectMapper();
		String json = objMapper.writeValueAsString(vend);
		
		mvc.perform(post("/vendedores")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isUnprocessableEntity());
	}
	
	@Test
	public void givenVendedorNewDtoDuplicatedCpf_whenPostVendedor_thenStatus422() throws Exception {
		
		VendedorNewDTO vend = new VendedorNewDTO();
		vend.setNome("Alana Mariana Pereira");
		vend.setCpf("09720484306");
		
		ObjectMapper objMapper = new ObjectMapper();
		String json = objMapper.writeValueAsString(vend);
		
		mvc.perform(post("/vendedores")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isUnprocessableEntity())
				.andExpect(content().json("{'errors':[{'fieldName':'cpf'}]}"));
	}
	
	@Test
	public void givenVendedorNewDtoDuplicatedName_whenPostVendedor_thenStatus422() throws Exception {
		
		VendedorNewDTO vend = new VendedorNewDTO();
		vend.setNome("guilherme victor thiago corte real");
		vend.setCpf("65492807889");
		
		ObjectMapper objMapper = new ObjectMapper();
		String json = objMapper.writeValueAsString(vend);
		
		mvc.perform(post("/vendedores")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isUnprocessableEntity())
				.andExpect(content().json("{'errors':[{'fieldName':'nome'}]}"));
	}
	
	@Test
	public void givenVendedorUpdtDtoValid_whenPutVendedor_thenStatus204() throws Exception {
		
		VendedorUpdtDTO vend = new VendedorUpdtDTO();
		vend.setNome("Nome Atualizado");
		
		ObjectMapper objMapper = new ObjectMapper();
		String json = objMapper.writeValueAsString(vend);
		
		mvc.perform(put("/vendedores/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isNoContent());
	}
	
	@Test
	public void givenVendedorUpdtDtoInvalidName_whenPutVendedor_thenStatus422() throws Exception {
		
		VendedorUpdtDTO vend = new VendedorUpdtDTO();
		vend.setNome("Nom");
		
		ObjectMapper objMapper = new ObjectMapper();
		String json = objMapper.writeValueAsString(vend);
		
		mvc.perform(put("/vendedores/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isUnprocessableEntity())
				.andExpect(content().json("{'errors':[{'fieldName':'nome'}]}"));
	}
	
	@Test
	public void givenVendedorUpdtDtoExistName_whenPutVendedor_thenStatus422() throws Exception {
		
		VendedorUpdtDTO vend = new VendedorUpdtDTO();
		vend.setNome("Isabella Maria Novaes");
		
		ObjectMapper objMapper = new ObjectMapper();
		String json = objMapper.writeValueAsString(vend);
		
		mvc.perform(put("/vendedores/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isUnprocessableEntity())
				.andExpect(content().json("{'errors':[{'fieldName':'nome'}]}"));
	}
	
	@Test
	public void givenFindAllVendedores_whenGetVendedores_thenStatus200() throws Exception {
		
		mvc.perform(get("/vendedores/page")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void givenVendedorId_whenDeleteVendedor_thenStatus204() throws Exception {
		
		mvc.perform(delete("/vendedores/3")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());
	}
	
	@Test
	public void givenVendedorNewDtoWhichIsACliente_whenPostVendedor_thenStatus201() throws Exception {
		
		VendedorNewDTO obj = new VendedorNewDTO();
		obj.setNome("Tomás Kauê José Cardoso");
		obj.setCpf("35838452340");
		
		ObjectMapper objMapper = new ObjectMapper();
		String json = objMapper.writeValueAsString(obj);
		
		mvc .perform(post("/vendedores")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(status().isCreated());
	}
}
