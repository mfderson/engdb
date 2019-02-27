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
	public void quandoEncontraPeloId_EntaoRetornaVendedor() {
		
		// given
		Vendedor guilherme = new Vendedor(null, "Guilherme Victor Thiago Corte Real", "09720484306");
		vendedorRepository.save(guilherme);
		
		// when
		Optional<Vendedor> vendEncontrato = vendedorRepository.findById(guilherme.getId());
		
		//then
		assertThat(vendEncontrato.get().getNome()).isEqualTo(guilherme.getNome());
	}
}
