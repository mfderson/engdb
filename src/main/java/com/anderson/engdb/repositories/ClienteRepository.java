package com.anderson.engdb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.anderson.engdb.domain.Cliente;
import com.anderson.engdb.domain.Vendedor;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Transactional(readOnly = true)
	Vendedor findByCpf(String cpf);
}
