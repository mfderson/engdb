package com.anderson.engdb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.anderson.engdb.domain.Vendedor;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {

	@Transactional(readOnly = true)
	Optional<Vendedor> findByCpf(String cpf);
}
