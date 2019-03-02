package com.anderson.engdb.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.anderson.engdb.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Transactional(readOnly = true)
	Optional<Cliente> findByCpf(String cpf);
	
	@Transactional(readOnly = true)
	Optional<Cliente> findByNomeIgnoreCase(String nome);
	
	@Transactional(readOnly = true)
	Page<Cliente> findAllByVendedorId(Integer id, Pageable pageable);
}
