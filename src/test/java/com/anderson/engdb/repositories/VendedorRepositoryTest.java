package com.anderson.engdb.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.anderson.engdb.domain.Vendedor;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VendedorRepositoryTest {

	@Autowired
	private VendedorRepository vendedorRepository;
	
	@Test
	public void testSucessoBuscaPorId_entaoRetornaVendedor() {
		
		// given
		Vendedor vendGuilherme = new Vendedor(null, "Guilherme Victor Thiago Corte Real", "09720484306");
		vendedorRepository.save(vendGuilherme);
		
		// when
		Optional<Vendedor> vendEncontrado = vendedorRepository.findById(vendGuilherme.getId());
		
		//then
		assertThat(vendEncontrado.get().getNome()).isEqualTo(vendGuilherme.getNome());
	}
	
	@Test
	public void testSucessoBuscaPorIdInexistente_entaoRetornaNull() {
		
		// given
		
		// when
		Optional<Vendedor> vendEncontrado = vendedorRepository.findById(10000);
		
		// then
		assertThat(vendEncontrado).isEmpty();
	}
	
	@Test
	public void testSucessoBuscaPorCpf_entaoRetornaVendedor() {
		
		// given
		Vendedor vendIsabella = new Vendedor(null, "Isabella Maria Novaes", "06558847264");
		vendedorRepository.save(vendIsabella);
		
		// when
		Optional<Vendedor> vendEncontrado = vendedorRepository.findByCpf("06558847264");
		
		// then
		assertThat(vendIsabella.getCpf()).isEqualTo(vendEncontrado.get().getCpf());
	}
	
	@Test
	public void testSucessoBuscaPorCpfInexistente_entaoRetornaNull() {
		
		// given
		
		// when
		Optional<Vendedor> vendEncontrado = vendedorRepository.findByCpf("01010101010");
		
		// then
		assertThat(vendEncontrado).isNull();
	}
}
