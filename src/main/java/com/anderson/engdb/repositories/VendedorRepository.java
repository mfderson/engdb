package com.anderson.engdb.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.anderson.engdb.domain.Vendedor;

@Repository
public interface VendedorRepository extends PagingAndSortingRepository<Vendedor, Integer> {

	@Transactional(readOnly = true)
	Optional<Vendedor> findByCpf(String cpf);
	
	@Transactional(readOnly = true)
	Optional<Vendedor> findByNomeIgnoreCase(String nome);
}
